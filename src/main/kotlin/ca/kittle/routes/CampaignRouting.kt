package ca.kittle.routes

import ca.kittle.models.UserSession
import ca.kittle.repositories.CampaignRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

val campaignRepository = CampaignRepository()

fun Route.campaignRouting() {

    authenticate {
        route("/api/campaigns") {
            get("/ddb") {
                val (accountId, _, vttId, vttKey) = call.sessions.get<UserSession>() ?:
                    return@get call.respondText(NO_SESSION, status = HttpStatusCode.Unauthorized)
                if (vttKey.isBlank())
                    return@get call.respondText(NO_COBALT, status = HttpStatusCode.Unauthorized)
                val campaigns = ddbProxy.campaigns(vttKey) ?: return@get call.respondText(
                    "No campaigns found on DDB. DDB id is $vttId",
                    status = HttpStatusCode.BadRequest
                )
                if (vttId > 0) {
                    campaignRepository.cacheCampaigns(campaigns.filter { it.dmId.toInt() == vttId }, accountId)
                }

                call.respond(campaigns)
            }
        }
    }

}
