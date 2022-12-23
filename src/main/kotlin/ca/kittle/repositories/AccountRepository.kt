package ca.kittle.repositories

import ca.kittle.integrations.Database
import ca.kittle.integrations.Database.dbQuery
import ca.kittle.models.*
import mu.KotlinLogging
import org.mindrot.jbcrypt.BCrypt

class AccountRepository {
    private val logger = KotlinLogging.logger {}

    suspend fun getAccountByUsername(username: String): Account? = dbQuery {
        return@dbQuery toAccount(AccountDO.find { Accounts.username eq username }.singleOrNull())
    }

    suspend fun isUsernameAvailable(username: String): Boolean = dbQuery {
        return@dbQuery AccountDO.find { Accounts.username eq username }.empty()
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

    private fun toAccount(a: AccountDO?): Account? {
        if (a == null)
            return null
        return Account(a.username, a.password, a.email, a.active)
    }

//    val sequelId = Accounts.integer("sequel_id").uniqueIndex()
//    val name = Accounts.varchar("name", 50)
//    val director = Accounts.varchar("director", 50)

//    fun isUsernameAvailable(username: String): Boolean {
//        logger.debug { "Checking is username is available." }
//        val query = "SELECT count(username) as total from accounts a WHERE LOWER(a.username)=?"
//        db.connect().use { conn ->
//            conn.prepareStatement(query).use { stmt ->
//                stmt.setString(1, username)
//                stmt.executeQuery().use { rs ->
//                    while(rs.next())
//                        return rs.getInt("total") == 0
//                }
//            }
//        }
//        return false
//    }

}
