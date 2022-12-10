package ca.kittle.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.campaignRouting() {

    route("/api/campaigns") {
        get("") {
            val campaigns = ddbProxy.campaigns() ?:
            return@get call.respondText("Missing character id", status = HttpStatusCode.BadRequest)
            call.respond(campaigns)
        }
    }

}
