package ca.kittle.models.integrations;

public record DdbEncounterMonster(String groupId,
                                  long id,
                                  String uniqueId,
                                  String name,
                                  int order,
                                  int quantity,
                                  String notes,
                                  String index,
                                  int currentHitPoints,
                                  int temporaryHitPoints,
                                  int maximumHitPoints,
                                  int initiative) {
}

