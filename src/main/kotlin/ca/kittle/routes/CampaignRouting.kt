package ca.kittle.routes

import ca.kittle.models.UserSession
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Route.campaignRouting() {

    authenticate {
        route("/api/campaigns") {
            get("/ddb") {
                val userSession = call.sessions.get<UserSession>()
                val ddbKey = userSession?.vttKey ?:
                    return@get call.respondText("No DDB cobalt key configured", status = HttpStatusCode.ProxyAuthenticationRequired)
                val campaigns = ddbProxy.campaigns(ddbKey) ?: return@get call.respondText(
                    "No campaigns found on DDB. DDB id is ${userSession?.vttId}",
                    status = HttpStatusCode.BadRequest
                )
                call.respond(campaigns)
            }
        }
    }

}
