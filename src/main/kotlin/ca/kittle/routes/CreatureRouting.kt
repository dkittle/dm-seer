package ca.kittle.routes

import ca.kittle.integrations.mapping.DdbCreature
import ca.kittle.repositories.CreatureRepository
import ca.kittle.repositories.EncounterRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

val creatureRepository = CreatureRepository()
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
            get("/ddb/{id?}") {
                val id = call.parameters["id"] ?: return@get call.respondText(
                    "Missing ddb creature id",
                    status = HttpStatusCode.BadRequest
                )
                val creature = ddbProxy.creature(id.toLong()) ?: return@get call.respondText(
                    "No creature found with id $id",
                    status = HttpStatusCode.NotFound
                )
                if (creature.stats.isEmpty())
                    return@get call.respondText(
                        "Invalid creature found with id $id",
                        status = HttpStatusCode.BadRequest
                    )
                ddbProxy.cacheAvatars(creature)
                val new = mapping.createCreature(creature)
                //            creatureRepository.createCreature(new, "DDB", creature.id)
                call.respond(new)
                //            call.respond(creature)
            }
            get("/ddb/search/{term?}") {
//                val term = call.parameters["term"] ?: return@get call.respondText(
//                    "Missing ddb creature search term",
//                    status = HttpStatusCode.BadRequest
//                )
                val term = call.parameters["term"] ?: ""
                val creatures = ddbProxy.searchCreatures(term) ?: return@get call.respondText(
                    "No creatures found with term $term",
                    status = HttpStatusCode.NotFound
                )
                call.respond(creatures)
            }
        }
    }

//        get("/{id?}") {
//            val id = call.parameters["id"] ?:
//            return@get call.respondText("Missing encounter id", status = HttpStatusCode.BadRequest)
//            val encounter = encounterRepository.encounter(id.toLong(), null) ?:
//            return@get call.respondText("No encounter found with id $id", status = HttpStatusCode.NotFound)
//            call.respond(encounter)
//        }

}
