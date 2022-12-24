package ca.kittle.integrations

import ca.kittle.integrations.mapping.DdbCreature
import ca.kittle.integrations.spells.DdbItems
import ca.kittle.integrations.spells.DdbSpells
import ca.kittle.integrations.spells.Spell
import ca.kittle.models.integrations.*
import ca.kittle.models.integrations.character.DdbCharacter
import ca.kittle.models.integrations.creature.Creature
import ca.kittle.models.integrations.creature.DdbCreatures
import ca.kittle.models.integrations.encounter.DdbEncounter
import ca.kittle.models.integrations.encounter.DdbEncounters
import ca.kittle.models.integrations.encounter.Encounter
import ca.kittle.models.integrations.item.Item
import ca.kittle.routes.support.CharacterIds
import ca.kittle.models.integrations.tersecharacter.DdbTerseCharacterResponse
import com.nfeld.jsonpathkt.JsonPath
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.cio.*
import io.ktor.utils.io.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import mu.KotlinLogging
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.regex.Pattern

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
    private val DDB_KNOWN_SPELLS_SERVICE = "https://character-service.dndbeyond.com/character/v5/game-data/always-known-spells"
    private val DDB_PREPARED_SPELLS_SERVICE = "https://character-service.dndbeyond.com/character/v5/game-data/always-prepared-spells"
    private val DDB_SPELLS_SERVICE = "https://character-service.dndbeyond.com/character/v5/game-data/spells"
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

    private suspend fun authenticate(cobaltKey: String): Unit {
        if (System.currentTimeMillis() > cobaltTokenTtl || cobaltToken.isEmpty()) {
            logger.debug { "Getting auth token from ddb" }
            val client = jsonClient()
            val response = client.post("https://auth-service.dndbeyond.com/v1/cobalt-token") {
                headers {
                    append(HttpHeaders.Accept, "application/json")
                    append(HttpHeaders.Cookie, "CobaltSession=$cobaltKey")
                }
            }
            val result: DdbAuthResponse = response.body()
            cobaltToken = result.token
            val ttl = if (result.ttl < 5*60*1000) 5*60*1000 else result.ttl
            cobaltTokenTtl = System.currentTimeMillis() + (ttl * .85).toLong()
            logger.debug { "Token is good for ${result.ttl / 60} minutes" }
            client.close()
        }
    }

    suspend fun config(cobaltKey: String): String? {
        authenticate(cobaltKey)
        logger.debug { "Getting ddb user's configuration" }
        val client = jsonClient()
        val response = client.get(DDB_CONFIG_SERVICE) {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.ContentType, "application/json")
                append(HttpHeaders.Cookie, "cobalt-token=$cobaltToken; CobaltSession=$cobaltKey")
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

    suspend fun campaigns(cobaltKey: String): List<DdbCampaign>? {
        authenticate(cobaltKey)
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
        val tmp: String = response.body()
        logger.debug { tmp }
        val result: DdbCampaignResponse = response.body()
        return setSplashUrls(result.data)
    }

    suspend fun getDmCampaignId(): Long {
        logger.debug { "Getting a campaign id for the ddb user" }
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
            return 0
        val result: DdbCampaignResponse = response.body()
        return getCampaignId(result.data)
    }

    suspend fun userCharacters(cobaltKey: String, id: Long): List<DdbCharacterHeadline>? {
        authenticate(cobaltKey)
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

    suspend fun characters(cobaltKey: String, ids: List<Long>): List<ca.kittle.models.integrations.tersecharacter.Character>? {
        authenticate(cobaltKey)
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

    suspend fun items(cobaltKey: String): List<Item>? {
        authenticate(cobaltKey)
        val campaignId = getDmCampaignId()
        logger.debug { "Getting items" }
        val client = jsonClient()
        val response = client.get(DDB_ITEM_SERVICE + "&campaignId=$campaignId") {
            headers {
                append(HttpHeaders.UserAgent, USER_AGENT)
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.Authorization, "Bearer $cobaltToken")
            }
        }
        logger.info { "Status ${response.status}" }
        if (response.status != HttpStatusCode.OK)
            return null
        val result: DdbItems = response.body()
        return result.items
    }


    fun classIdForName(name: String): Int {
        return when (name.lowercase()) {
            "barbarian" -> 9
            "bard" -> 1
            "cleric" -> 2
            "druid" -> 3
            "fighter" -> 10
            "monk" -> 11
            "paladin" -> 4
            "ranger" -> 5
            "rogue" -> 12
            "sorcerer" -> 6
            "warlock" -> 7
            "wizard" -> 8
            "artificer" -> 252717
            "graviturgy" -> 400661
            "chronurgy" -> 400659
            else -> 0
        }
    }

    // "Druid", "Cleric", "Paladin", "Artificer"
    suspend fun spells(cobaltKey: String, klass: String): List<Spell>? {
        authenticate(cobaltKey)
        val campaignId = getDmCampaignId()
        val classId = classIdForName(klass)
        logger.debug { "Getting spells for $klass" }
        val client = jsonClient()
        val known: List<Spell> = if (listOf(2, 3, 4, 252717).contains(classId)) {
            val url = "$DDB_KNOWN_SPELLS_SERVICE?classId=$classId&classLevel=20&sharingSetting=2&campaignId=$campaignId"
            logger.debug { "Known spell endpoint $url" }
            val response = client.get(url) {
                headers {
                    append(HttpHeaders.Accept, "application/json")
                    append(HttpHeaders.Authorization, "Bearer $cobaltToken")
                }
            }
            logger.info { "Status ${response.status}" }
            if (response.status != HttpStatusCode.OK)
                return null
            val r: DdbSpells = response.body()
            r.spells
        } else
            listOf<Spell>()

        val url = "$DDB_SPELLS_SERVICE?classId=$classId&classLevel=20&sharingSetting=2&campaignId=$campaignId"
        logger.debug { "Spell endpoint $url" }
        val response = client.get(url) {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.Authorization, "Bearer $cobaltToken")
            }
        }
        logger.info { "Status ${response.status}" }
        if (response.status != HttpStatusCode.OK)
            return null
        val result: DdbSpells = response.body()
        val spells = mutableListOf<Spell>()
        result.spells.map {
            spells.add(it)
        }
        if (known.isNotEmpty())
            known.map {
                spells.add(it)
            }
        return spells
    }

    suspend fun encounters(cobaltKey: String): List<Encounter>? {
        authenticate(cobaltKey)
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

    suspend fun encounter(cobaltKey: String, id: String): Encounter? {
        authenticate(cobaltKey)
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

    suspend fun searchCreatures(cobaltKey: String, term: String): List<Creature>? {
        authenticate(cobaltKey)
        logger.debug { "Searching ddb creatures: $term" }
        val client = jsonClient()
        val url =
            "$DDB_CREATURE_SERIVCE?search=$term&skip=0&take=50&showHomebrew=f&sources=1&sources=8&sources=15"
        logger.debug { url }
        logger.debug { cobaltToken }
        val response = client.get(url) {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.Authorization, "Bearer $cobaltToken")
            }
        }
        client.close()
        if (response.status != HttpStatusCode.OK)
            return null
        val result: DdbCreatures = response.body()
        logger.debug { "Total results: ${result.pagination?.total}" }
        return result.creatures.filter { it.isReleased }
    }

    suspend fun creature(cobaltKey: String, id: Long): Creature? {
        authenticate(cobaltKey)
        logger.debug { "Getting a ddb creature" }
        val client = jsonClient()
        val response = client.get("$DDB_CREATURE_SERIVCE?ids=$id") {
            headers {
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

    suspend fun cacheAvatars(creature: Creature) {
        val url = DdbCreature.fixCreatureUrl(creature.avatarUrl)
        val large = DdbCreature.fixCreatureUrl(creature.largeAvatarUrl ?: "")
        val basic = DdbCreature.fixCreatureUrl(creature.basicAvatarUrl ?: "")
        val name = creature.name
        val folder = "./avatars/${name.take(1).lowercase()}"
        if (Files.exists(Paths.get("$folder/$name-${Url(url).pathSegments.last()}"))) {
            logger.debug { "File $folder/$name-${Url(url).pathSegments.last()} already downloaded."}
            return
        }
        logger.debug { "Caching creature avatars for $name" }
        if (url.isNotBlank()) {
            val client = HttpClient(CIO)
            logger.debug { "Storing avatar for $name in $folder/" }
            val u = Url(url)
            val file = File("$folder/$name-${u.pathSegments.last()}")
            client.get(url).bodyAsChannel().copyAndClose(file.writeChannel())
            logger.debug { "Finished storing avatar from $url" }
            client.close()
        }
        if (large.isNotBlank()) {
            val client = HttpClient(CIO)
            logger.debug { "Storing large avatar for $name in $folder/" }
            val u = Url(large)
            val file = File("$folder/$name-large-${u.pathSegments.last()}")
            client.get(large).bodyAsChannel().copyAndClose(file.writeChannel())
            logger.debug { "Finished storing large avatar from $large" }
            client.close()
        }
        if (basic.isNotBlank()) {
            val client = HttpClient(CIO)
            logger.debug { "Storing basic avatar for $name in $folder/" }
            val u = Url(basic)
            val file = File("$folder/$name-basic-${u.pathSegments.last()}")
            client.get(basic).bodyAsChannel().copyAndClose(file.writeChannel())
            logger.debug { "Finished storing basic avatar from $basic" }
            client.close()
        }
    }

    suspend fun cacheItemAvatars(item: Item) {
        val url = item.avatarUrl ?: ""
        val large = item.largeAvatarUrl ?: ""
        val name = item.name ?: "unknown"
        if (url.isEmpty() && large.isEmpty())
            return
        val folder = "./items/${name.take(1).lowercase()}"
        if (url.isNotBlank() && Files.exists(Paths.get("$folder/$name-${Url(url).pathSegments.last()}"))) {
            logger.debug { "File $folder/$name-${Url(url).pathSegments.last()} already downloaded."}
            return
        }
        logger.debug { "Caching item avatars for $name" }
        if (url.isNotBlank()) {
            val client = HttpClient(CIO)
            logger.debug { "Storing avatar for $name in $folder/" }
            val u = Url(url)
            val file = File("$folder/$name-${u.pathSegments.last()}")
            client.get(url).bodyAsChannel().copyAndClose(file.writeChannel())
            logger.debug { "Finished storing avatar from $url" }
            client.close()
        }
        if (large.isNotBlank()) {
            val client = HttpClient(CIO)
            logger.debug { "Storing large avatar for $name in $folder/" }
            val u = Url(large)
            val file = File("$folder/$name-large-${u.pathSegments.last()}")
            client.get(large).bodyAsChannel().copyAndClose(file.writeChannel())
            logger.debug { "Finished storing large avatar from $large" }
            client.close()
        }
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

    fun userCache(userId: Long): Long {
        val decoder = Base64.getDecoder()
        var m = Pattern.compile("(.*?)\\.(.*?)\\.").matcher(cobaltToken)
        val userDetails = if (m.find()) m.group(2) else ""
        val jwt = decoder.decode(userDetails)
        m = Pattern.compile("nameidentifier\":\"(\\d*?)\"").matcher(String(jwt))
        return if (m.find()) m.group(1)?.toLong() ?: 0  else 0
    }

    fun getCampaignId(campaigns: List<DdbCampaign>?): Long {
        if (campaigns.isNullOrEmpty()) {
            logger.debug { "Could not get DM's campaigns" }
        }
        val userId = userCache(0)
        return campaigns?.filter { it.dmId == userId }?.last()?.id ?: 0
    }

    private fun codifyConfig(input: String) {
        val jsonpath = JsonPath("$.raceGroups.['id','name']")
        val raceGroups = jsonpath.readFromJson<List<Map<String, String>>>(input)
        logger.debug { raceGroups }
    }

    companion object {

    }
}

//eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6IjEwNzMyNjM4MyIsImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL25hbWUiOiJEb25EYURNIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvZW1haWxhZGRyZXNzIjoiZG9uQGtpdHRsZS5jYSIsImRpc3BsYXlOYW1lIjoiRG9uRGFETSIsImh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9jbGFpbXMvcm9sZSI6IlJlZ2lzdGVyZWQgVXNlcnMiLCJodHRwOi8vc2NoZW1hcy5kbmRiZXlvbmQuY29tL3dzLzIwMTkvMDgvaWRlbnRpdHkvY2xhaW1zL3N1YnNjcmliZXIiOiJUcnVlIiwiaHR0cDovL3NjaGVtYXMuZG5kYmV5b25kLmNvbS93cy8yMDE5LzA4L2lkZW50aXR5L2NsYWltcy9zdWJzY3JpcHRpb250aWVyIjoiTWFzdGVyIiwibmJmIjoxNjcxNTY2OTA1LCJleHAiOjE2NzE1NjcyMDUsImlzcyI6ImRuZGJleW9uZC5jb20iLCJhdWQiOiJkbmRiZXlvbmQuY29tIn0.o4YeFV72HiU1zwaXyBjfpFQ8f98f_VVMdo9-XtgXDuE
