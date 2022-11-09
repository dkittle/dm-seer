package ca.kittle.models.app;

import java.util.List;

public record Encounter(String name, List<EncounterGroup> groups, Location location) {
}
