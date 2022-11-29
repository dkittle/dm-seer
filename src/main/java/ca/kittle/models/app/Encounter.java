package ca.kittle.models.app;

import java.util.List;

public record Encounter(long id, String name, String campaign, String location, int suggestedAPL) {
}
//public record Encounter(long id, String name, List<EncounterGroup> groups, Location location) {
//}
