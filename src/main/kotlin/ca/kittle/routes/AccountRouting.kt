package ca.kittle.routes

import ca.kittle.models.AccountUsername
import ca.kittle.models.Credentials
import ca.kittle.services.IdentityAuth
import ca.kittle.models.NewAccount
import ca.kittle.repositories.AccountRepository
import ca.kittle.routes.support.FALSE
import ca.kittle.routes.support.TRUE
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.mindrot.jbcrypt.BCrypt

val accountRepository = AccountRepository()

fun Route.accountRouting(identityAuth: IdentityAuth) {

    post("/api/login") {
        val credentials = call.receive<Credentials>()
        val account = accountRepository.getAccountByUsername(credentials.username)
        if (account == null || !BCrypt.checkpw(credentials.password, account.password)) {
            return@post call.respondText("Invalid credentials", status = HttpStatusCode.Unauthorized)
        }
        call.respond(mapOf("token" to identityAuth.signedToken(account.username)))
    }

    post("/api/register") {
        val possible = call.receive<NewAccount>()
        val account = accountRepository.getAccountByUsername(possible.username)
        if (account != null)
            error("Account with that username already exists")
        val newAccount = accountRepository.create(possible)
        if (newAccount == null)
            error("Could not create account")
        else
            call.respond(mapOf("token" to identityAuth.signedToken(newAccount.username)))
    }

    post("/api/isUsernameAvailable") {
        val data: AccountUsername = call.receive<AccountUsername>()
        val result = accountRepository.isUsernameAvailable(data.username.lowercase())
        val message = if (result) TRUE else FALSE
        call.respond(message)
    }

    authenticate {
        route("/api/account") {
        }
    }

}
