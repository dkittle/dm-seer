package ca.kittle.repositories

import ca.kittle.integrations.Database
import ca.kittle.models.Encounter
import mu.KotlinLogging
import java.sql.ResultSet


class EncounterRepository {
    private val logger = KotlinLogging.logger {}
    private val db = Database()


    fun encounter(id: Long, accountId: Long?): Encounter? {
        logger.debug { "Retrieving a specific encounter" }
        db.connect().use { conn ->
            conn.createStatement().use { stmt ->
                stmt.executeQuery(encounterQuery(1, id)).use { rs ->
                    while (rs.next())
                        return readEncounter(rs)
                }
            }
        }
        return null
    }

    fun encounters(accountId: Long?): List<Encounter> {
        logger.debug { "Retrieving all encounters for current user." }
        val result = arrayListOf<Encounter>()
        db.connect().use { conn ->
            conn.createStatement().use { stmt ->
                stmt.executeQuery(encounterQuery(null, accountId)).use { rs ->
                    while (rs.next())
                        result.add(readEncounter(rs))
                }
            }
        }
        return result
    }

    fun readEncounter(rs: ResultSet): Encounter {
        return Encounter(rs.getLong("id"),
                rs.getString("name"),
                rs.getString("dm_name"),
                rs.getString("campaign_name"),
                rs.getLong("locationId"),
                rs.getString("location"),
                rs.getString("title"),
                rs.getInt("suggested_acl"));
    }

    companion object {
        fun encounterQuery(id: Long? = null, accountId: Long? = null): String {
            val specific = if (id != null) " e.id=$id" else ""
            val account = if (accountId != null) " e.created_by=$accountId" else ""
            val where = if (!specific.isEmpty() || !account.isEmpty()) "WHERE" else ""
            val joiner = if (!specific.isEmpty() && !account.isEmpty()) "AND" else ""
            return "SELECT e.*, c.name AS campaign, l.id AS location_id, l.name AS location, r.name AS room " +
                    "ac.username AS dm_name, s.title " +
                    "FROM encounters e " +
                    "LEFT OUTER JOIN accounts ac ON e.dm_id = ac.id " +
                    "LEFT OUTER JOIN campaigns c ON e.campaign_id = c.id " +
                    "LEFT OUTER JOIN locations l ON e.location_id = l.id " +
                    "LEFT OUTER JOIN rooms r ON e.parent_location_id = r.id " +
                    "LEFT OUTER JOIN sources s ON e.source_id = s.id " +
                    "$where $account $joiner ${specific}";
        }
    }
}
