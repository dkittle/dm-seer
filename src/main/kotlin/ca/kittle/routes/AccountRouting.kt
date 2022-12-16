package ca.kittle.routes

import ca.kittle.repositories.AccountRepository
import ca.kittle.routes.support.AccountUsername
import ca.kittle.routes.support.AccountUsernameAvailable
import ca.kittle.util.ResultOf
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

val accountRepository = AccountRepository()

fun Route.accountRouting() {

    route("/api/account") {
        post("/isUsernameAvailable") {
            val data: AccountUsername = call.receive<AccountUsername>()
            val result = accountRepository.isUsernameAvailable(data.username.lowercase())
            call.respond(AccountUsernameAvailable(result, data.username))
        }
    }


}
