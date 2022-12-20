package ca.kittle.integrations

import ca.kittle.models.integrations.*
import ca.kittle.models.integrations.character.DdbCharacter
import ca.kittle.models.integrations.creature.Creature
import ca.kittle.models.integrations.creature.DdbCreatures
import ca.kittle.models.integrations.encounter.DdbEncounter
import ca.kittle.models.integrations.encounter.DdbEncounters
import ca.kittle.models.integrations.encounter.Encounter
import ca.kittle.routes.support.CharacterIds
import ca.kittle.models.integrations.tersecharacter.DdbTerseCharacterResponse
import com.nfeld.jsonpathkt.JsonPath
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

class DdbProxy(private val cobaltSession: String?) {

    private var cobaltToken = ""
    private var cobaltTokenTtl: Long = 0

    private val USER_AGENT =
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 13_0_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36";

    private val DDB_ENCOUNTER_SERVICE = "https://encounter-service.dndbeyond.com/v1/encounters"
    private val DDB_NEW_CHARACTER_SERVICE = "https://character-service-scds.dndbeyond.com/v2/characters"
    private val DDB_LIST_CHARACTERS = "https://character-service.dndbeyond.com/character/v5/characters/list"
    private val DDB_CREATURE_SERIVCE = "https://monster-service.dndbeyond.com/v1/Monster"
    private val DDB_ITEM_SERVICE = "https://character-service.dndbeyond.com/character/v5/game-data/items?sharingSetting=2"
    private val DDB_CONFIG_SERVICE = "https://www.dndbeyond.com/api/config/json"

    @OptIn(ExperimentalSerializationApi::class)
    private fun jsonClient(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = false
                    isLenient = true
                    ignoreUnknownKeys = true
                    explicitNulls = false
                })
            }
        }
    }

    private suspend fun authenticate(): Unit {
        if (cobaltSession != null && (System.currentTimeMillis() > cobaltTokenTtl || cobaltToken.isEmpty())) {
            logger.debug { "Getting auth token from ddb" }
            val client = jsonClient()
            val response = client.post("https://auth-service.dndbeyond.com/v1/cobalt-token") {
                headers {
                    append(HttpHeaders.Accept, "application/json")
                    append(HttpHeaders.Cookie, "CobaltSession=$cobaltSession")
                }
            }
            val result: DdbAuthResponse = response.body()
            cobaltToken = result.token
            cobaltTokenTtl = System.currentTimeMillis() + (result.ttl * .85).toLong()
            client.close()
        }
    }

    suspend fun config(): String? {
        authenticate()
        logger.debug { "Getting ddb user's configuration" }
        val client = jsonClient()
        val response = client.get(DDB_CONFIG_SERVICE) {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                append(HttpHeaders.Cookie, "cobalt-token=$cobaltToken; CobaltSession=$cobaltSession")
            }
        }
        client.close()
        logger.debug { "Config response: ${response.status}" }
        if (response.status != HttpStatusCode.OK)
            return null
        val result: String = response.body()
        codifyConfig(result)
        return result
    }

    suspend fun campaigns(): List<DdbCampaign>? {
        authenticate()
        logger.debug { "Getting all ddb user's campaigns" }
        val client = jsonClient()
        val response = client.get("https://www.dndbeyond.com/api/campaign/active-campaigns") {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                append(HttpHeaders.Cookie, "cobalt-token=$cobaltToken; CobaltSession=$cobaltSession")
            }
        }
        client.close()
        if (response.status != HttpStatusCode.OK)
            return null
        val result: DdbCampaignResponse = response.body()
        return setSplashUrls(result.data)
    }

    suspend fun userCharacters(id: Long): List<DdbCharacterHeadline>? {
        authenticate()
        logger.debug { "Getting list of user's ddb characters" }
        val client = jsonClient()
        val response = client.get("$DDB_LIST_CHARACTERS?userId=$id") {
            headers {
                append(HttpHeaders.UserAgent, USER_AGENT)
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.Authorization, "Bearer $cobaltToken")
            }
        }
        logger.info { "Status ${response.status}" }
        if (response.status != HttpStatusCode.OK)
            return null
        val result: DdbUserCharactersResponse = response.body()
//        logger.debug { result }
        return result.data.characters
    }

    suspend fun characters(ids: List<Long>): List<ca.kittle.models.integrations.tersecharacter.Character>? {
        authenticate()
        logger.debug { "Getting several ddb characters: $ids" }
        val client = jsonClient()
        val response = client.post(DDB_NEW_CHARACTER_SERVICE) {
            headers {
                append(HttpHeaders.UserAgent, USER_AGENT)
                append(HttpHeaders.ContentType, "application/json")
                append(HttpHeaders.Authorization, "Bearer $cobaltToken")
            }
            setBody(CharacterIds(ids))
        }
        client.close()
        logger.info { response.status }
        if (response.status != HttpStatusCode.OK)
            return null
        val result: DdbTerseCharacterResponse = response.body()
        return result.foundCharacters
    }

    suspend fun character(id: String): DdbCharacter? {
        logger.debug { "Getting a public ddb character" }
        val client = jsonClient()
        val response = client.get("https://character-service.dndbeyond.com/character/v5/character/$id") {
            headers {
                append(HttpHeaders.UserAgent, USER_AGENT)
                append(HttpHeaders.ContentType, "application/json")
                append(HttpHeaders.Cookie, "cobalt-token=$cobaltToken; CobaltSession=$cobaltSession")
            }
        }
        client.close()
        if (response.status != HttpStatusCode.OK)
            return null
        val result: DdbCharacterResponse = response.body()
        return result.data
    }

    suspend fun encounters(): List<Encounter>? {
        authenticate()
        logger.debug { "Getting all ddb user's encounters" }
        val encounters = arrayListOf<Encounter>()
        var offset = 0
        var numberEncounters = 0
        val client = jsonClient()
        while (offset == 0 || offset < 10) {
//        while (offset == 0 || offset < numberEncounters) {
            val url = if (offset > 0) "$DDB_ENCOUNTER_SERVICE?skip=$offset&take=10" else DDB_ENCOUNTER_SERVICE
            val response = client.get(url) {
                headers {
                    append(HttpHeaders.UserAgent, USER_AGENT)
                    append(HttpHeaders.Accept, "application/json")
                    append(HttpHeaders.Authorization, "Bearer $cobaltToken")
                }
            }
            logger.info { "Status ${response.status}" }
            if (response.status != HttpStatusCode.OK)
                return null
            val tmp: String = response.body()
            logger.debug { tmp }
            val result: DdbEncounters = response.body()
            numberEncounters = result.pagination?.total ?: return encounters
            encounters.addAll(result.encounters)
            offset += 10
        }
        return encounters
    }

    suspend fun encounter(id: String): Encounter? {
        logger.debug { "Getting a private ddb encounter" }
        val client = jsonClient()
        val response = client.get("$DDB_ENCOUNTER_SERVICE/$id") {
            headers {
                append(HttpHeaders.UserAgent, USER_AGENT)
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.Authorization, "Bearer $cobaltToken")
            }
        }
        client.close()
        if (response.status != HttpStatusCode.OK)
            return null
        val result: DdbEncounter = response.body()
        return result.encounter
    }

    suspend fun searchCreatures(term: String): List<Creature>? {
        logger.debug { "Searching ddb creatures" }
        val client = jsonClient()
        val response = client.get("$DDB_CREATURE_SERIVCE?search=$term") {
            headers {
                append(HttpHeaders.UserAgent, USER_AGENT)
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.Authorization, "Bearer $cobaltToken")
            }
        }
        client.close()
        if (response.status != HttpStatusCode.OK)
            return null
        val tmp: String = response.body()
        logger.debug { tmp }
        val result: DdbCreatures = response.body()
        return result.creatures
    }

    suspend fun creature(id: Long): Creature? {
        logger.debug { "Getting a ddb creature" }
        val client = jsonClient()
        val response = client.get("$DDB_CREATURE_SERIVCE?ids=$id") {
            headers {
                append(HttpHeaders.UserAgent, USER_AGENT)
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.Authorization, "Bearer $cobaltToken")
            }
        }
        client.close()
        if (response.status != HttpStatusCode.OK)
            return null
        val tmp: String = response.body()
        logger.debug { tmp }
        val result: DdbCreatures = response.body()
        if (result.creatures.size != 1)
            logger.warn { "Got ${result.creatures.size} creatures back for a single id ($id)." }
        return result.creatures.get(0)
    }

    private fun setSplashUrls(campaigns: List<DdbCampaign>): List<DdbCampaign> {
        val result = arrayListOf<DdbCampaign>()
        campaigns.map { campaign ->
            var splash = campaign.splashUrl
            if (campaign.splashUrl.isEmpty()) {
                if (campaign.name.lowercase().contains("avernus"))
                    splash = "https://www.dndbeyond.com/attachments/6/468/0ecor-cover-4k.jpg"
                if (campaign.name.lowercase().contains("candlekeep"))
                    splash = "https://media.dndbeyond.com/compendium-images/cm/c43LH2y2Gcaxb3V2/CM-cover-2560.jpg"
                if (campaign.name.lowercase().contains("strahd"))
                    splash = "https://www.dndbeyond.com/attachments/8/220/cos-cover-4k.jpg"
                if (campaign.name.lowercase().contains("icespire") ||
                    campaign.name.lowercase().contains("storm lord") ||
                    campaign.name.lowercase().contains("sleeping dragon") ||
                    campaign.name.lowercase().contains("divine contention")
                )
                    splash = "https://www.dndbeyond.com/attachments/5/748/cover.jpg"
                if (campaign.name.lowercase().contains("stormwreck"))
                    splash = "https://media.dndbeyond.com/compendium-images/dosi/DDb16Nqxd68tCXNB/dosi-coverart.jpg"
                if (campaign.name.lowercase().contains("dragonlance"))
                    splash = "https://media.dndbeyond.com/compendium-images/sotdq/kHXEUZ8D0saAJOvE/sotdq-cover-art.jpg"
                if (campaign.name.lowercase().contains("saltmarsh"))
                    splash = "https://www.dndbeyond.com/attachments/5/372/gos_fullcoverart.jpg"
                if (campaign.name.lowercase().contains("hoard"))
                    splash = "https://www.dndbeyond.com/attachments/6/718/cover4k.jpg"
                if (campaign.name.lowercase().contains("frostmaiden") ||
                    campaign.name.lowercase().contains("rime"))
                    splash = "https://media.dndbeyond.com/compendium-images/idrotf/7Av7Gi2DxDtdzZPt/idrotf-cover.jpg"
                if (campaign.name.lowercase().contains("radiant"))
                    splash = "https://media.dndbeyond.com/compendium-images/jttrc/GaddytVaCSBwjL0n/jttrc-cover.jpg"
                if (campaign.name.lowercase().contains("phandalin") ||
                    campaign.name.lowercase().contains("phandelver")
                ) {
                    splash = "https://www.dndbeyond.com/attachments/2/730/lmopcover.jpg"
                }
                if (campaign.name.lowercase().contains("abyss"))
                    splash = "https://www.dndbeyond.com/attachments/2/731/ootacover.jpg"
                if (campaign.name.lowercase().contains("apocalypse"))
                    splash = "https://www.dndbeyond.com/attachments/2/732/potacover.jpg"
                if (campaign.name.lowercase().contains("tiamat"))
                    splash = "https://www.dndbeyond.com/attachments/6/896/rot-cover-4k.jpg"
                if (campaign.name.lowercase().contains("thunder"))
                    splash = "https://www.dndbeyond.com/attachments/2/734/sktcover.png"
                if (campaign.name.lowercase().contains("yawning"))
                    splash = "https://www.dndbeyond.com/attachments/8/263/credits-cover.jpg"
                if (campaign.name.lowercase().contains("witchlight"))
                    splash = "https://media.dndbeyond.com/compendium-images/twbtw/JtUXxjur9QWtb7E3/TWBtW-cover.jpg"
                if (campaign.name.lowercase().contains("annihilation") ||
                    campaign.name.lowercase().contains("toa"))
                    splash = "https://www.dndbeyond.com/attachments/2/971/toa_cover.jpg"
                if (campaign.name.lowercase().contains("heist"))
                    splash = "https://www.dndbeyond.com/attachments/4/376/waterdeep-dragon-heist.jpg"
                if (campaign.name.lowercase().contains("mad mage"))
                    splash = "https://www.dndbeyond.com/attachments/5/542/dungeon_madmage_cover.jpg"
                if (campaign.name.lowercase().contains("strixhaven"))
                    splash = "https://media.dndbeyond.com/compendium-images/sacoc/zBbqVwSoOPBn2DCM/DACoC-cover-full.jpg"
                if (campaign.name.lowercase().contains("spelljam"))
                    splash = "https://media.dndbeyond.com/compendium-images/sja/9h8GiE7HbKsyOg18/sja-cover-art.jpg"
            }
            result.add(DdbCampaign(campaign.id, campaign.name, campaign.dmUsername, campaign.dmId, splash,
                campaign.dateCreated, campaign.playerCount))
        }
        return result
    }

    private fun codifyConfig(input: String) {
        val jsonpath = JsonPath("$.raceGroups.['id','name']")
        val raceGroups = jsonpath.readFromJson<List<Map<String, String>>>(input)
        logger.debug { raceGroups }
    }

    companion object {

    }
}
