package ca.kittle.routes

import ca.kittle.integrations.DdbProxy
import ca.kittle.models.UserSession
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Route.spellRouting() {

    authenticate {
        route("/api/spells") {
            get("/ddb/{name}") {
                val (_, _, vttId, vttKey) = call.sessions.get<UserSession>() ?:
                    return@get call.respondText(NO_SESSION, status = HttpStatusCode.Unauthorized)
                if (vttKey.isBlank())
                    return@get call.respondText(NO_COBALT, status = HttpStatusCode.Unauthorized)
                val name = call.parameters["name"] ?: return@get call.respondText(
                    "Missing caster class name",
                    status = HttpStatusCode.BadRequest
                )
                val spells = DdbProxy(vttId, vttKey).spells(name) ?: return@get call.respondText(
                    "No spells found for $name",
                    status = HttpStatusCode.NotFound
                )
                call.respond(spells)
            }
        }
    }
//    route("/api/spell") {
//        get("/ddb/{id?}") {
//            val id = call.parameters["id"] ?:
//            return@get call.respondText("Missing encounter id", status = HttpStatusCode.BadRequest)
//            val encounter = ddbProxy.encounter(id) ?:
//            return@get call.respondText("No encounter found with id $id", status = HttpStatusCode.NotFound)
//            call.respond(encounter)
//        }
//        get("/{id?}") {
//            val id = call.parameters["id"] ?:
//            return@get call.respondText("Missing encounter id", status = HttpStatusCode.BadRequest)
//            val encounter = encounterRepository.encounter(id.toLong(), null) ?:
//            return@get call.respondText("No encounter found with id $id", status = HttpStatusCode.NotFound)
//            call.respond(encounter)
//        }


}
