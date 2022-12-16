package ca.kittle.repositories

import ca.kittle.integrations.Database
import ca.kittle.util.ResultOf
import mu.KotlinLogging
import java.sql.SQLException

class AccountRepository {
    private val logger = KotlinLogging.logger {}
    private val db = Database()

    fun isUsernameAvailable(username: String): Boolean {
        logger.debug { "Checking is username is available." }
        val query = "SELECT count(username) as total from accounts a WHERE LOWER(a.username)=?"
        db.connect().use { conn ->
            conn.prepareStatement(query).use { stmt ->
                stmt.setString(1, username)
                stmt.executeQuery().use { rs ->
                    while(rs.next())
                        return rs.getInt("total") == 0
                }
            }
        }
        return false
    }

}
