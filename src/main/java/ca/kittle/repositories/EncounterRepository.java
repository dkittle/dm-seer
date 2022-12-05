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

    public void createEncounter(Encounter encounter) {
        if (encounter.name() == null || encounter.name().isEmpty())
            throw new IllegalArgumentException("Encounter name cannot be null.");
        if (encounter.suggestedACL() < 1)
            throw new IllegalArgumentException("Suggested Average Character Level must be 1 or greater..");

        var record = "INSERT INTO encounters SET ";
    }
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

    public void addCreature(long encounterId, long creatureId, int number) {
        logger.info("Add a creature to an encounter");
        var encounterCheck = "SELECT id from encounters where id=?";
        var creatureCheck = "SELECT id from creatures where id=?";
        var addCreatureToEncounter = "INSERT INTO encounter_creates SET encounterId=? AND creatureId=? AND creature_numbers=?";

        try (Connection connection = DriverManager.getConnection(db.jdbcConnectString(), db.username(), db.password())) {
            try (PreparedStatement statement = connection.prepareStatement(encounterCheck)) {
                statement.setLong(1, encounterId);
                int i = statement.executeUpdate();
                if (i == 0)
                    throw new NotFoundException("Encounter id " + encounterId + " does not exist");
            }
            try (PreparedStatement statement = connection.prepareStatement(creatureCheck)) {
                statement.setLong(1, creatureId);
                int i = statement.executeUpdate();
                if (i == 0)
                    throw new NotFoundException("Creature id " + encounterId + " does not exist");
            }
            try (PreparedStatement statement = connection.prepareStatement(addCreatureToEncounter)) {
                statement.setLong(1, encounterId);
                statement.setLong(2, creatureId);
                statement.setLong(3, number);
                int i = statement.executeUpdate();
                if (i == 0)
                    throw new SQLException("Cannot add creatures to encounter");
            }

        } catch (SQLException e) {
            logger.error("Problem adding to encounter", e);
        }
    }

}
