package ca.kittle.routes

import ca.kittle.integrations.DdbProxy
import ca.kittle.models.UserSession
import ca.kittle.repositories.EncounterRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

val encounterRepository = EncounterRepository()

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
                val encounters = DdbProxy(vttId, vttKey).encounters()
//                encounterRepository.cacheEncounters(encounters, accountId)
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
