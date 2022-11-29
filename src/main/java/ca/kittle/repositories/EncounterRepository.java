package ca.kittle.repositories;

import ca.kittle.integrations.Database;
import ca.kittle.models.app.Encounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EncounterRepository {
    private static final Logger logger = LoggerFactory.getLogger(EncounterRepository.class);

    private final Database db = new Database();

    public List<Encounter> encounters(/** User user */) {
        logger.info("Get all encounters");
        var result = new ArrayList<Encounter>();
        var query = "SELECT e.*, c.campaign_name, l.name AS location FROM encounters e LEFT OUTER JOIN campaigns c ON e.campaign_id = c.id LEFT OUTER JOIN locations l ON e.location_id = l.id WHERE e.created_by=1";

        try (Connection connection = DriverManager.getConnection(db.jdbcConnectString(),db.username(),db.password());
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                result.add(new Encounter(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("campaign_name"),
                        rs.getString("location"),
                        rs.getInt("suggested_apl")));
            }
        } catch (SQLException e) {
            logger.error("Problem getting all encounters", e);
        }
        logger.debug("Returning {} encounters", result.size());
        return result;
    }

}
