package ca.kittle.routes

import ca.kittle.integrations.DdbProxy
import ca.kittle.integrations.mapping.DdbCreature
import ca.kittle.models.UserSession
import ca.kittle.repositories.CreatureDao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

val mapping = DdbCreature()

fun Route.creatureRouting() {

//    route("/api/creaturess") {
//        get("") {
//            val encounters = encounterRepository.encounters(1) ?:
//            return@get call.respondText("Could not get your encounters", status = HttpStatusCode.BadRequest)
//            call.respond(encounters)
//        }
//        get("/ddb") {
//            val encounters = ddbProxy.encounters() ?:
//            return@get call.respondText("Could not get your encounters", status = HttpStatusCode.BadRequest)
//            call.respond(encounters)
//        }
//    }
    authenticate {

        route("/api/creature") {
            get("/{id?}") {
                val id = call.parameters["id"] ?: return@get call.respondText(
                    "Missing encounter id",
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
                ddbProxy.cacheAvatars(creature)
                CreatureDao.cacheCreatureFromDdb(creature, accountId)
                val new = mapping.createCreature(creature)
                call.respond(new)
            }
            get("/ddb/search/{term?}") {
//                val term = call.parameters["term"] ?: return@get call.respondText(
//                    "Missing ddb creature search term",
//                    status = HttpStatusCode.BadRequest
//                )
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
    }

}
