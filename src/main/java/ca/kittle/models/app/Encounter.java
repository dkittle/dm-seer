package ca.kittle.models.app;

public record Encounter(long id, String name, String campaign, String location, int suggestedACL) {
}
//public record Encounter(long id, String name, List<EncounterGroup> groups, Location location) {
//}
