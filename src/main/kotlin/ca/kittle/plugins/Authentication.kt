package ca.kittle.plugins

import ca.kittle.services.IdentityAuth
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureAuthentication(identityAuth: IdentityAuth) {
    install(Authentication) {
        jwt {
            verifier(identityAuth.verifier)
            validate {
                UserIdPrincipal(it.payload.getClaim("username").asString())
            }
        }
    }
}
