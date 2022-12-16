package ca.kittle.routes

import ca.kittle.integrations.DdbProxy
import ca.kittle.routes.support.OK
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

val ddbProxy = DdbProxy(System.getenv("COBALT_TOKEN"))

fun Route.coreRouting() {


    get("/") {
        call.respondText("D&D is for everyone.", status = HttpStatusCode.OK)
    }

    get("/api/health") {
            call.respond(HttpStatusCode.OK, OK)
    }

}
