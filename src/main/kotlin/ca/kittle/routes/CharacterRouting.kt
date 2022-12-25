package ca.kittle.routes

import ca.kittle.integrations.DdbProxy
import ca.kittle.models.UserSession
import ca.kittle.routes.support.CharacterIds
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*


fun Route.characterRouting() {

    authenticate {
        route("/api/characters") {

            post("/ddb") {
                val (_, _, vttId, vttKey) = call.sessions.get<UserSession>() ?:
                    return@post call.respondText(NO_SESSION, status = HttpStatusCode.Unauthorized)
                if (vttKey.isBlank())
                    return@post call.respondText(NO_COBALT, status = HttpStatusCode.Unauthorized)
                val ids: CharacterIds = call.receive<CharacterIds>()
                val characters = DdbProxy(vttId, vttKey).characters(ids.characterids)
                    ?: return@post call.respondText(
                        "No character found with ids $ids",
                        status = HttpStatusCode.NotFound
                    )
                call.respond(characters)
            }

            get("/ddb") {
                val (_, _, vttId, vttKey) = call.sessions.get<UserSession>() ?:
                    return@get call.respondText(NO_SESSION, status = HttpStatusCode.Unauthorized)
                if (vttKey.isBlank())
                    return@get call.respondText(NO_COBALT, status = HttpStatusCode.Unauthorized)
                val characters = DdbProxy(vttId, vttKey).userCharacters()
                    ?: return@get call.respondText(
                        "No characters found for user id $vttId",
                        status = HttpStatusCode.NotFound
                    )
                call.respond(characters)
            }
        }
    }

    authenticate {
        route("/api/character") {
            get("/ddb/{id?}") {
                val (_, _, vttId, vttKey) = call.sessions.get<UserSession>() ?:
                return@get call.respondText(NO_SESSION, status = HttpStatusCode.Unauthorized)
                if (vttKey.isBlank())
                    return@get call.respondText(NO_COBALT, status = HttpStatusCode.Unauthorized)
                val id = call.parameters["id"] ?: return@get call.respondText(
                    "Missing character id",
                    status = HttpStatusCode.BadRequest
                )
                val character = DdbProxy(vttId, vttKey).character(id) ?: return@get call.respondText(
                    "No character found with id $id",
                    status = HttpStatusCode.NotFound
                )
                call.respond(character)
            }
        }
    }

}
