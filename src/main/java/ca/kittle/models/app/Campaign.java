package ca.kittle.models.app;

public record Campaign(long id, String name, String description, String publicNotes, String privateNotes, boolean official) {
}
