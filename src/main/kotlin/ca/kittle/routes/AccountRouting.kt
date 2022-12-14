package ca.kittle.routes

import ca.kittle.models.*
import ca.kittle.services.IdentityAuth
import ca.kittle.repositories.AccountDao
import ca.kittle.routes.support.FALSE
import ca.kittle.routes.support.Status
import ca.kittle.routes.support.TRUE
import com.auth0.jwt.exceptions.TokenExpiredException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import org.mindrot.jbcrypt.BCrypt


fun Route.accountRouting(identityAuth: IdentityAuth) {

    post("/api/login") {
        val credentials = call.receive<Credentials>()
        val account = AccountDao.getAccountByUsername(credentials.username)
        if (account == null || !BCrypt.checkpw(credentials.password, account.password)) {
            return@post call.respondText("Invalid credentials", status = HttpStatusCode.Unauthorized)
        }
        val ddb = AccountDao.getDDBAccount(account.id)
        call.sessions.set(UserSession(account.id, account.username, ddb?.vttId ?: 0, ddb?.vttKey ?: ""))
        call.respond(mapOf("token" to identityAuth.signedToken(account.username)))
    }

    post("/api/refresh") {
        val authToken = call.request.header("Authorization")
        try {
            identityAuth.verifier.verify(authToken?.drop(7))
        }
        catch (ignored: TokenExpiredException) { }
        val (accountId, username, vttId, vttKey) = call.sessions.get<UserSession>() ?:
            return@post call.respondText(NO_SESSION, status = HttpStatusCode.Unauthorized)
//        val ddb = AccountDao.getDDBAccount(vttId)
        call.sessions.set(UserSession(accountId, username, vttId, vttKey))
        call.respond(mapOf("token" to identityAuth.signedToken(username)))
    }

    post("/api/register") {
        val possible = call.receive<NewAccount>()
        val account = AccountDao.getAccountByUsername(possible.username)
        if (account != null)
            error("Account with that username already exists")
        val newAccount = AccountDao.create(possible)
        if (newAccount == null)
            error("Could not create account")
        else
            call.respond(mapOf("token" to identityAuth.signedToken(newAccount.username)))
    }

    post("/api/isUsernameAvailable") {
        val data: AccountUsername = call.receive<AccountUsername>()
        val result = AccountDao.isUsernameAvailable(data.username.lowercase())
        val message = if (result) TRUE else FALSE
        call.respond(message)
    }

    authenticate {
        route("/api/account") {
            post("/ddb") {
                val userSession = call.sessions.get<UserSession>() ?:
                    return@post call.respondText("No user session", status = HttpStatusCode.BadRequest)
                val data = call.receive<NewVttAccount>()
                val vttId = AccountDao.vttAccount(userSession.accountId, data.vttKey)
                call.sessions.set(UserSession(userSession.accountId, userSession.username, vttId, data.vttKey))
                call.respond(HttpStatusCode.Created, Status("OK", "DDB account link created"))
            }
        }
    }

}
