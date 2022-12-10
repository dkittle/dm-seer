package ca.kittle.routes

import ca.kittle.models.OK
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.coreRouting() {

    route("/api/health") {
        get("") {
            call.respond(HttpStatusCode.OK, OK)
        }
    }

}
