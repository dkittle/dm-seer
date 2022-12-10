package ca.kittle.repositories;

import ca.kittle.integrations.Database;
import ca.kittle.models.app.Campaign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Optional;

public class CampaignRepository {

    private static final Logger logger = LoggerFactory.getLogger(CampaignRepository.class);
    private final Database db = new Database();

    public Optional<Campaign> campaign(long id) {
        logger.info("Get a specific campaign");
        Campaign result = null;
        var query = "SELECT * from campaigns where campaigns.id=?";

        try (Connection connection = DriverManager.getConnection(db.jdbcConnectString(),db.username(),db.password());
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                return Optional.of(new Campaign(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getBoolean("official")));
        } catch (SQLException e) {
            logger.error("Problem getting campaign", e);
        }
        return Optional.empty();
    }

    public Optional<Campaign> campaignByName( String name) {
        logger.info("Get a specific campaign by name");
        var query = "SELECT * from campaigns where LOWER(campaigns.name)=?";

        try (Connection connection = DriverManager.getConnection(db.jdbcConnectString(),db.username(),db.password());
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name.toLowerCase());
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                return Optional.of(new Campaign(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getBoolean("official")));
        } catch (SQLException e) {
            logger.error("Problem getting campaign", e);
        }
        return Optional.empty();
    }

}
