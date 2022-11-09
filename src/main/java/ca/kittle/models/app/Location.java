package ca.kittle.models.app;

public record Location(long id, String name, LocationType type, long parentId) {
}

