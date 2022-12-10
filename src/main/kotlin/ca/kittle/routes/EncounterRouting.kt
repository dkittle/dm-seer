package ca.kittle.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.encounterRouting() {

    route("/api/encounters") {
        get("") {
            val encounters = ddbProxy.encounters() ?:
                return@get call.respondText("Could not get your encounters", status = HttpStatusCode.BadRequest)
            call.respond(encounters)
        }
    }

    get("/api/encounter/{id?}") {
        val id = call.parameters["id"] ?:
            return@get call.respondText("Missing encounter id", status = HttpStatusCode.BadRequest)
        val encounter = ddbProxy.encounter(id) ?:
            return@get call.respondText("No encounter found with id $id", status = HttpStatusCode.NotFound)
        call.respond(encounter)
    }

}
