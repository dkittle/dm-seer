package ca.kittle.models.app;

import jakarta.validation.constraints.NotNull;

public record Encounter(long id, @NotNull String name, String campaign, String location, int suggestedACL) {
}
//public record Encounter(long id, String name, List<EncounterGroup> groups, Location location) {
//}
