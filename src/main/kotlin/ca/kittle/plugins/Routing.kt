package ca.kittle.plugins

import ca.kittle.routes.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*

fun Application.configureRouting() {

    routing {
        coreRouting()
        campaignRouting()
        characterRouting()
        encounterRouting()
    }
}
