package ca.kittle.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.itemRouting() {

    route("/api/config") {
        get("/ddb") {
            val config = ddbProxy.config() ?:
                return@get call.respondText("Could not get your config", status = HttpStatusCode.BadRequest)
            call.respond(config)
        }
    }
    route("/api/items") {
        get("") {
            val encounters = encounterRepository.encounters(1) ?:
            return@get call.respondText("Could not get your encounters", status = HttpStatusCode.BadRequest)
            call.respond(encounters)
        }
        get("/ddb") {
            val encounters = ddbProxy.encounters() ?:
            return@get call.respondText("Could not get your encounters", status = HttpStatusCode.BadRequest)
            call.respond(encounters)
        }
    }
    route("/api/item") {
        get("/ddb/{id?}") {
            val id = call.parameters["id"] ?:
            return@get call.respondText("Missing encounter id", status = HttpStatusCode.BadRequest)
            val encounter = ddbProxy.encounter(id) ?:
            return@get call.respondText("No encounter found with id $id", status = HttpStatusCode.NotFound)
            call.respond(encounter)
        }
        get("/{id?}") {
            val id = call.parameters["id"] ?:
            return@get call.respondText("Missing encounter id", status = HttpStatusCode.BadRequest)
            val encounter = encounterRepository.encounter(id.toLong(), null) ?:
            return@get call.respondText("No encounter found with id $id", status = HttpStatusCode.NotFound)
            call.respond(encounter)
        }
    }


}
