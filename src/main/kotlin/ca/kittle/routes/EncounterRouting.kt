package ca.kittle.routes

import ca.kittle.integrations.DdbProxy
import ca.kittle.models.UserSession
import ca.kittle.repositories.CreatureDao
import ca.kittle.repositories.EncounterDao
import ca.kittle.services.CreatureService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*


fun Route.encounterRouting() {

    authenticate {
        route("/api/encounters") {
//        get("") {
//            val encounters = encounterRepository.encounters(1) ?:
//                return@get call.respondText("Could not get your encounters", status = HttpStatusCode.BadRequest)
//            call.respond(encounters)
//        }
            get("/ddb") {
                val (accountId, _, vttId, vttKey) = call.sessions.get<UserSession>() ?:
                    return@get call.respondText(NO_SESSION, status = HttpStatusCode.Unauthorized)
                if (vttKey.isBlank())
                    return@get call.respondText(NO_COBALT, status = HttpStatusCode.Unauthorized)
                val ddbProxy = DdbProxy(vttId, vttKey)
                val encounters = ddbProxy.encounters()
                encounters.forEach {encounter ->
                    encounter.monsters.forEach { monster ->
                        val id = CreatureService.getCachedCreatureId(ddbProxy, monster.id, accountId)
                    }
                }
                EncounterDao.cacheEncountersFromDdb(encounters, accountId)
                call.respond(encounters)
            }
        }
    }

    authenticate {
        route("/api/encounter") {
            get("/ddb/{id?}") {
                val (_, _, vttId, vttKey) = call.sessions.get<UserSession>() ?:
                    return@get call.respondText(NO_SESSION, status = HttpStatusCode.Unauthorized)
                if (vttKey.isBlank())
                    return@get call.respondText(NO_COBALT, status = HttpStatusCode.Unauthorized)
                val id = call.parameters["id"] ?: return@get call.respondText(
                    "Missing encounter id",
                    status = HttpStatusCode.BadRequest
                )
                val encounter = DdbProxy(vttId, vttKey).encounter(id) ?: return@get call.respondText(
                    "No encounter found with id $id",
                    status = HttpStatusCode.NotFound
                )
                call.respond(encounter)
            }
//        get("/{id?}") {
//            val id = call.parameters["id"] ?:
//                return@get call.respondText("Missing encounter id", status = HttpStatusCode.BadRequest)
//            val encounter = encounterRepository.encounter(id.toLong(), null) ?:
//                return@get call.respondText("No encounter found with id $id", status = HttpStatusCode.NotFound)
//            call.respond(encounter)
//        }
        }
    }

}
