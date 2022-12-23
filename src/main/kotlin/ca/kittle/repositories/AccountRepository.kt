package ca.kittle.repositories

import ca.kittle.integrations.Database
import ca.kittle.integrations.Database.dbQuery
import ca.kittle.models.*
import mu.KotlinLogging
import org.jetbrains.exposed.sql.*
import org.mindrot.jbcrypt.BCrypt

class AccountRepository {
    private val logger = KotlinLogging.logger {}

    suspend fun getAccountByUsername(username: String): Account? = dbQuery {
        return@dbQuery toAccount(AccountDO.find { Accounts.username eq username }.singleOrNull())
    }

    suspend fun isUsernameAvailable(username: String): Boolean = dbQuery {
        return@dbQuery AccountDO.find { Accounts.username.lowerCase() eq username }.empty()
    }

    suspend fun create(newAccount: NewAccount): Account? = dbQuery {
        return@dbQuery toAccount(AccountDO.new {
            username = newAccount.username
            password = BCrypt.hashpw(newAccount.password, BCrypt.gensalt())
            email = newAccount.email
            active = false
            createdOn = Database.now
            lastLogin = Database.now
        })
    }

    suspend fun getDDBAccount(accountId: Int): VttAccount? = dbQuery {
        VttAccounts.select { (VttAccounts.account eq accountId) and (VttAccounts.vttName eq "DDB") }
            .mapNotNull { toVttAccount(it) }
            .singleOrNull()
    }

    suspend fun vttAccount(accountId: Int, newVttAccount: NewVttAccount): Int = dbQuery {
        val old = VttAccounts.select { (VttAccounts.account eq accountId) and (VttAccounts.vttName eq "DDB") }
            .mapNotNull { toVttAccount(it) }
            .singleOrNull()
            ?: return@dbQuery VttAccounts.insertAndGetId {
                it[account] = accountId
                it[vttName] = newVttAccount.vttName
                it[vttId] = newVttAccount.vttId
                it[vttKey] = newVttAccount.vttKey
            }.value
        return@dbQuery VttAccounts.update( { VttAccounts.id eq old.id }) {
            it[vttName] = newVttAccount.vttName
            it[vttId] = newVttAccount.vttId
            it[vttKey] = newVttAccount.vttKey
        }
    }

//    suspend fun getUserByEmail(email: String): User? = dbQuery {
//        Users.select {
//            (Users.email eq email)
//        }.mapNotNull { toUser(it) }
//            .singleOrNull()
//    }
//
//    private fun toUser(row: ResultRow): User =
//        User(
//            id = row[Users.id],
//            email = row[Users.email],
//            active = row[Users.active],
//            password = row[Users.password]
//        )

    private fun toAccount(a: AccountDO?): Account? {
        return if (a == null) null else Account(a.id.value, a.username, a.password, a.email, a.active)
    }

    private fun toVttAccount(row: ResultRow): VttAccount =
        VttAccount(row[VttAccounts.id].value,
            row[VttAccounts.account].value,
            row[VttAccounts.vttName],
            row[VttAccounts.vttId],
            row[VttAccounts.vttKey]
        )



//    val email = URLDecoder.decode("email", "UTF-8")
//    val phone = URLDecoder.decode("phone", "UTF-8")
//
//    Users.find {
//        if (email != null) (Users.email eq email) else Op.TRUE
//            .and(if (phone != null) (Users.phone eq phone) else Op.TRUE)
//    }

    companion object {
        private val logger = KotlinLogging.logger {}
    }
}
