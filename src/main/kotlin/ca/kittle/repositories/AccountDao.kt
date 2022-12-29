package ca.kittle.repositories

import ca.kittle.integrations.Database
import ca.kittle.integrations.Database.dbQuery
import ca.kittle.integrations.DdbProxy
import ca.kittle.models.*
import mu.KotlinLogging
import org.jetbrains.exposed.sql.*
import org.mindrot.jbcrypt.BCrypt

object AccountDao {
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

    suspend fun vttAccount(accountId: Int, vttKey: String): Int {
        val token = DdbProxy(0, vttKey).authenticate()
        val id = getDdbIdFromCobaltToken(token)
        createVttAccount(accountId, "DDB", id, vttKey)
        return id
    }

    suspend fun createVttAccount(accountId: Int, ddbName: String, ddbId: Int, ddbKey: String): Int = dbQuery {
        val old = VttAccounts.select { (VttAccounts.account eq accountId) and (VttAccounts.vttName eq "DDB") }
            .mapNotNull { toVttAccount(it) }
            .singleOrNull()
            ?: return@dbQuery VttAccounts.insertAndGetId {
                it[account] = accountId
                it[vttName] = ddbName
                it[vttId] = ddbId
                it[vttKey] = ddbKey
            }.value
        return@dbQuery VttAccounts.update( { VttAccounts.id eq old.id }) {
            it[vttKey] = ddbKey
        }
    }

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

}
