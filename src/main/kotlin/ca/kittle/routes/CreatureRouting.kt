package ca.kittle.routes

import ca.kittle.integrations.DdbProxy
import ca.kittle.integrations.mapping.DdbCreature
import ca.kittle.models.UserSession
import ca.kittle.repositories.CreatureDao
import ca.kittle.routes.support.OK
import ca.kittle.services.CreatureService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

val mapping = DdbCreature()

fun Route.creatureRouting() {

    route("/api/creatures") {

        get("/find") {
            val terms = call.request.queryParameters["terms"]
            if (terms.isNullOrBlank())
                return@get call.respondText("You must supply at least one search term",
                    status = HttpStatusCode.BadRequest
                )
            val (accountId, _, _, _) = call.sessions.get<UserSession>() ?:
                return@get call.respondText(NO_SESSION, status = HttpStatusCode.Unauthorized)
            call.respond(CreatureService.findCreatures(terms, accountId))
        }

        get("/ddb/find/{term?}") {
            val (_, _, vttId, vttKey) = call.sessions.get<UserSession>() ?:
            return@get call.respondText(NO_SESSION, status = HttpStatusCode.Unauthorized)
            if (vttKey.isBlank())
                return@get call.respondText(NO_COBALT, status = HttpStatusCode.Unauthorized)
            val term = call.parameters["term"] ?: ""
            val creatures = DdbProxy(vttId, vttKey).searchCreatures(term) ?: return@get call.respondText(
                "No creatures found with term $term",
                status = HttpStatusCode.NotFound
            )
            call.respond(creatures)
        }
    }

    authenticate {

        route("/api/creature") {

            get("/{id?}") {
                val id = call.parameters["id"] ?: return@get call.respondText(
                    "Missing creature id",
                    status = HttpStatusCode.BadRequest
                )
                val creature = CreatureDao.getCreature(id.toInt())
                    ?: return@get call.respondText("No creature found with id $id", status = HttpStatusCode.NotFound)
                call.respond(creature)
            }

            get("/ddb/{id?}") {
                val (accountId, _, vttId, vttKey) = call.sessions.get<UserSession>() ?:
                    return@get call.respondText(NO_SESSION, status = HttpStatusCode.Unauthorized)
                if (vttKey.isBlank())
                    return@get call.respondText(NO_COBALT, status = HttpStatusCode.Unauthorized)
                val id = call.parameters["id"] ?: return@get call.respondText(
                    "Missing ddb creature id",
                    status = HttpStatusCode.BadRequest
                )
                val ddbProxy = DdbProxy(vttId, vttKey)
                val creature = ddbProxy.creature(id.toInt()) ?: return@get call.respondText(
                    "No creature found with id $id",
                    status = HttpStatusCode.NotFound
                )
                if (creature.stats.isEmpty())
                    return@get call.respondText(
                        "Invalid creature found with id $id",
                        status = HttpStatusCode.BadRequest
                    )
                CreatureDao.cacheCreatureFromDdb(creature, accountId)
                call.respond(status = HttpStatusCode.Created, OK)
            }
        }
    }

}
