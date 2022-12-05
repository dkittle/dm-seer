package ca.kittle.repositories;

import ca.kittle.integrations.Database;
import ca.kittle.models.app.Campaign;
import ca.kittle.models.app.Location;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class CampaignRepository {

    private static final Logger logger = LoggerFactory.getLogger(CampaignRepository.class);
    private final Database db = new Database();

    public Campaign campaign(long id) {
        logger.info("Get a specific campaign");
        Campaign result = null;
        var query = "SELECT * from campaigns where campaigns.id=?";

        try (Connection connection = DriverManager.getConnection(db.jdbcConnectString(),db.username(),db.password());
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result = new Campaign(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getBoolean("official"));
            }
        } catch (SQLException e) {
            logger.error("Problem getting campaign", e);
        }
        return result;
    }

    public Campaign campaignByName(@NotNull String name) {
        logger.info("Get a specific campaign by name");
        Campaign result = null;
        var query = "SELECT * from campaigns where LOWER(campaigns.name)=?";

        try (Connection connection = DriverManager.getConnection(db.jdbcConnectString(),db.username(),db.password());
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name.toLowerCase());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result = new Campaign(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getBoolean("official"));
            }
        } catch (SQLException e) {
            logger.error("Problem getting location", e);
        }
        return result;
    }


}
