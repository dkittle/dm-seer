package ca.kittle.routes

import ca.kittle.integrations.DdbProxy
import ca.kittle.models.customerStorage
import ca.kittle.models.integrations.tersecharacter.CharacterIds
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

val ddbProxy = DdbProxy(System.getenv("COBALT_TOKEN"))

fun Route.characterRouting() {

    post("/api/characters") {
        val ids: CharacterIds = call.receive<CharacterIds>()
        val characters = ddbProxy.characters(ids.characterids) ?:
            return@post call.respondText("No character found with ids $ids", status = HttpStatusCode.NotFound)
        call.respond(characters)
    }

    get("/api/character/{id?}") {
        val id = call.parameters["id"] ?:
            return@get call.respondText("Missing character id", status = HttpStatusCode.BadRequest)
        val character = ddbProxy.character(id) ?:
            return@get call.respondText("No character found with id $id", status = HttpStatusCode.NotFound)
        call.respond(character)
    }

}
