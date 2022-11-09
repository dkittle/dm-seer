package ca.kittle.models.app;

public record Combatant(long id, String identifier, Creature creature, int initiative, boolean villainous) {
}
