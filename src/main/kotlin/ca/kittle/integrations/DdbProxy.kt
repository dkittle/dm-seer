package ca.kittle.integrations

import ca.kittle.models.integrations.*
import ca.kittle.models.integrations.character.DdbCharacter
import ca.kittle.models.integrations.encounter.DdbEncounter
import ca.kittle.models.integrations.encounter.DdbEncounters
import ca.kittle.models.integrations.encounter.Encounter
import ca.kittle.models.integrations.tersecharacter.CharacterIds
import ca.kittle.models.integrations.tersecharacter.DdbTerseCharacterResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
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
        return result.data
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
        val tmp: String = response.body()
        logger.info { "Character: ${tmp}"}
        val result: DdbCharacterResponse = response.body()
        return result.data
    }

    suspend fun encounters(): List<Encounter>? {
        authenticate()
        logger.debug { "Getting all ddb user's encounters" }
        val client = jsonClient()
        val response = client.get(DDB_ENCOUNTER_SERVICE) {
            headers {
                append(HttpHeaders.UserAgent, USER_AGENT)
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.Authorization, "Bearer $cobaltToken")
            }
        }
        logger.info { "Status ${response.status}" }
        if (response.status != HttpStatusCode.OK)
            return null
        val result: DdbEncounters = response.body()
        return result.encounters
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

}
