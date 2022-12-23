package ca.kittle.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.campaignRouting() {

    authenticate {
        route("/api/campaigns") {
            get("/ddb") {
                val campaigns = ddbProxy.campaigns() ?: return@get call.respondText(
                    "Missing character id",
                    status = HttpStatusCode.BadRequest
                )
                call.respond(campaigns)
            }
        }
    }

}
