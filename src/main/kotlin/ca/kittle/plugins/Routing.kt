package ca.kittle.plugins

import ca.kittle.services.IdentityAuth
import ca.kittle.routes.*
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting(identityAuth: IdentityAuth) {

    routing {
        coreRouting()
        accountRouting(identityAuth)
        campaignRouting()
        characterRouting()
        creatureRouting()
        encounterRouting()
        itemRouting()
        spellRouting()
    }
}
