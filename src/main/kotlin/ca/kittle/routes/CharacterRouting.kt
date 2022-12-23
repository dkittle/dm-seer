package ca.kittle.routes

import ca.kittle.routes.support.CharacterIds
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.characterRouting() {

    post("/api/characters") {
        val ids: CharacterIds = call.receive<CharacterIds>()
        val characters = ddbProxy.characters(ids.characterids) ?:
            return@post call.respondText("No character found with ids $ids", status = HttpStatusCode.NotFound)
        call.respond(characters)
    }


    get("/api/characters/{id?}") {
        val id = call.parameters["id"] ?:
            return@get call.respondText("Missing user's ddb id", status = HttpStatusCode.BadRequest)
        val characters = ddbProxy.userCharacters(id.toLong()) ?:
            return@get call.respondText("No characters found for user id $id", status = HttpStatusCode.NotFound)
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
