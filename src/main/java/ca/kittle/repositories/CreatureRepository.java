package ca.kittle.repositories;

import ca.kittle.integrations.Database;
import ca.kittle.models.app.Creature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Optional;

public class CreatureRepository {

    private static final Logger logger = LoggerFactory.getLogger(CreatureRepository.class);
    private final Database db = new Database();

    public Optional<Creature> creature(long id) {
        logger.info("Get a specific creature");
        var query = "SELECT c.*, cr.label as crLabel, cr.challenge_rating FROM creatures c LEFT OUTER JOIN creature_crs cr ON c.challenge_rating_id = cr.id where c.id=?";

        try (Connection connection = DriverManager.getConnection(db.jdbcConnectString(), db.username(), db.password());
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                return Optional.of(new Creature(rs.getLong("id"),
                        rs.getString("name"),
                        "",
                        rs.getString("crLabel"),
                        rs.getFloat("challenge_rating")));
        } catch (SQLException e) {
            logger.error("Problem getting creature", e);
        }
        return Optional.empty();
    }

//    public Campaign campaignByName(@NotNull String name) {
//        logger.info("Get a specific campaign by name");
//        Campaign result = null;
//        var query = "SELECT * from campaigns where LOWER(campaigns.name)=?";
//
//        try (Connection connection = DriverManager.getConnection(db.jdbcConnectString(),db.username(),db.password());
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, name.toLowerCase());
//            try (ResultSet rs = statement.executeQuery()) {
//                result = oneCreatureFromRS(rs);
//            }
//        } catch (SQLException e) {
//            logger.error("Problem getting campaign", e);
//            throw new NotFoundException("Campaign " + name + " not found.");
//        }
//        return result;
//    }


}
