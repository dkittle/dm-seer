package ca.kittle.repositories;

import ca.kittle.integrations.Database;
import ca.kittle.models.app.Encounter;
import ca.kittle.models.app.Location;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LocationRepository {
    private static final Logger logger = LoggerFactory.getLogger(LocationRepository.class);
    private final Database db = new Database();

    public Optional<Location> location(long id) {
        logger.info("Get a specific location");
        Location result = null;
        var query = "SELECT * from locations where locations.id=?";

        try (Connection connection = DriverManager.getConnection(db.jdbcConnectString(),db.username(),db.password());
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next())
                    return Optional.of(new Location(rs.getLong("id"),
                            rs.getString("name")));
            }
        } catch (SQLException e) {
            logger.error("Problem getting location", e);
        }
        return Optional.empty();
    }

    public Optional<Location> locationByName(@NotNull String name) {
        logger.info("Get a specific location by name");
        Location result = null;
        var query = "SELECT * from locations where LOWER(locations.name)=?";

        try (Connection connection = DriverManager.getConnection(db.jdbcConnectString(),db.username(),db.password());
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name.toLowerCase());
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next())
                    return Optional.of(new Location(rs.getLong("id"),
                            rs.getString("name")));
            }
        } catch (SQLException e) {
            logger.error("Problem getting location", e);
        }
        return Optional.empty();
    }


}
