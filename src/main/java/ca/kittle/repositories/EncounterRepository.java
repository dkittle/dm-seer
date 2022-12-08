package ca.kittle.repositories;

import ca.kittle.integrations.Database;
import ca.kittle.models.app.Campaign;
import ca.kittle.models.app.Encounter;
import ca.kittle.models.app.Location;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EncounterRepository {
    private static final Logger logger = LoggerFactory.getLogger(EncounterRepository.class);

    private final Database db = new Database();
    private final CampaignRepository campaigns = new CampaignRepository();
    private final LocationRepository locations = new LocationRepository();
    private final CreatureRepository creatures = new CreatureRepository();

    public Optional<Encounter> encounter(long id) {
        logger.info("Get a specific encounter");
        var query = "SELECT e.*, c.name, l.name AS location FROM encounters e LEFT OUTER JOIN campaigns c ON e.campaign_id = c.id LEFT OUTER JOIN locations l ON e.location_id = l.id WHERE e.id=?";

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next())
                    return Optional.of(readEncounter(rs));
            }
        } catch (SQLException e) {
            logger.error("Problem getting encounter " + id, e);
        }
        return Optional.empty();
    }

    @NotNull
    private static Encounter readEncounter(ResultSet rs) throws SQLException {
        return new Encounter(rs.getLong("id"),
                rs.getString("name"),
                rs.getString("name"),
                rs.getString("location"),
                rs.getInt("suggested_acl"));
    }

    public boolean createEncounter(Encounter encounter) {
        if (encounter.suggestedACL() < 1)
            throw new IllegalArgumentException("Suggested Average Character Level must be 1 or greater..");

        logger.info("Create an encounter");
        var record = "INSERT INTO encounters (name, campaign_id, location_id, suggested_acl, created_by) VALUES (?,?,?,?,1)";
        Long campaignId = null;
        Long locationId = null;

        if (encounter.campaign() != null && !encounter.campaign().isEmpty()) {
            var campaign = campaigns.campaignByName(encounter.campaign());
            if (campaign.isPresent())
                campaignId = campaign.get().id();
        }

        if (encounter.location() != null && !encounter.location().isEmpty()) {
            var location = locations.locationByName(encounter.location());
            if (location.isPresent())
                locationId = location.get().id();
        }

        try (Connection conn = db.connect();
             PreparedStatement stmt = conn.prepareStatement(record)) {
            stmt.setString(1, encounter.name());
            stmt.setLong(2, campaignId);
            stmt.setLong(3, locationId);
            stmt.setInt(4, encounter.suggestedACL());
            if (stmt.executeUpdate() == 1)
                return true;
        } catch (SQLException e) {
            logger.error("Problem adding to encounter", e);
        }
        return false;
    }

    public List<Encounter> encounters(/** User user */) {
        logger.info("Get all encounters");
        var result = new ArrayList<Encounter>();
        var query = "SELECT e.*, c.name, l.name AS location FROM encounters e LEFT OUTER JOIN campaigns c ON e.campaign_id = c.id LEFT OUTER JOIN locations l ON e.location_id = l.id WHERE e.created_by=1";

        try (Connection conn = db.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                result.add(readEncounter(rs));
            }
        } catch (SQLException e) {
            logger.error("Problem getting all encounters", e);
        }
        logger.debug("Returning {} encounters", result.size());
        return result;
    }

    public boolean addCreature(long encounterId, long creatureId, int number) {
        var addCreatureToEncounter = "INSERT INTO encounter_creatures (encounter_id, creature_id, creature_numbers) VALUES (?,?,?)";

        logger.info("Encounter {} is being updated", encounterId);
        var encounter = encounter(encounterId);
        logger.info("Add {} creature to an encounter", creatureId);
        var creature = creatures.creature(creatureId);

        if (encounter.isEmpty() || creature.isEmpty())
            return false;

        try (Connection connection = DriverManager.getConnection(db.jdbcConnectString(), db.username(), db.password());
             PreparedStatement statement = connection.prepareStatement(addCreatureToEncounter)) {
            statement.setLong(1, encounterId);
            statement.setLong(2, creatureId);
            statement.setLong(3, number);
            if (statement.executeUpdate() == 1)
                return true;
        } catch (SQLException e) {
            logger.error("Problem adding to encounter", e);
        }
        return false;
    }

}
