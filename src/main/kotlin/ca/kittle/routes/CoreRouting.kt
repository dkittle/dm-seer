package ca.kittle.routes

import ca.kittle.integrations.Database
import ca.kittle.integrations.DdbProxy
import ca.kittle.models.Accounts
import ca.kittle.models.VttAccounts
import ca.kittle.routes.support.OK
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

val NO_SESSION = "No user session"
val NO_COBALT = "No DDB cobalt key configured"

fun Route.coreRouting() {


    get("/") {
        call.respondText("D&D is for everyone.", status = HttpStatusCode.OK)
    }

//    authenticate {
        post("/api/db") {
            Database.initDb()
            call.respond(HttpStatusCode.OK, OK)
        }
//    }

    get("/api/health") {
        call.respond(HttpStatusCode.OK, OK)
    }

}
