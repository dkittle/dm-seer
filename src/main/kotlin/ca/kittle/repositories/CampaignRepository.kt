package ca.kittle.repositories

import ca.kittle.integrations.Database
import ca.kittle.models.app.Campaign
import mu.KotlinLogging
import java.util.*

class CampaignRepository {
    private val logger = KotlinLogging.logger {}
    private val db = Database()


//    fun campaign(id: Long): Campaign? {
//        logger.info { "Get a specific campaign" }
//        val query = "SELECT * from campaigns where campaigns.id=?"
//
//        db.connect().use { conn ->
//            conn.prepareStatement(query).use { stmt ->
//                stmt.setString(1, id)
//                stmt.executeQuery().use { rs ->
//                    while(rs.next())
//                        return rs.getInt("total") == 0
//                }
//            }
//        }

//        try (Connection connection = DriverManager.getConnection(db.jdbcConnectString(),db.username(),db.password());
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setLong(1, id);
//            ResultSet rs = statement.executeQuery();
//            if (rs.next())
//                return Optional.of(new Campaign(rs.getLong("id"),
//                        rs.getString("name"),
//                        rs.getBoolean("official")));
//        } catch (SQLException e) {
//            logger.error("Problem getting campaign", e);
//        }
//        return Optional.empty()
//    }

//    fun campaignByName(name: String?): Optional<Campaign?>? {
//        CampaignRepository.logger.info("Get a specific campaign by name")
//        val query = "SELECT * from campaigns where LOWER(campaigns.name)=?"

//        try (Connection connection = DriverManager.getConnection(db.jdbcConnectString(),db.username(),db.password());
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, name.toLowerCase());
//            ResultSet rs = statement.executeQuery();
//            if (rs.next())
//                return Optional.of(new Campaign(rs.getLong("id"),
//                        rs.getString("name"),
//                        rs.getBoolean("official")));
//        } catch (SQLException e) {
//            logger.error("Problem getting campaign", e);
//        }
//        return Optional.empty()
//    }

}
