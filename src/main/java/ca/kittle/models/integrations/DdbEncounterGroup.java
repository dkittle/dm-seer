package ca.kittle.models.integrations;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 * Used to put groups of monsters in turn order
 * @param id The groupId of the creature
 * @param order the order of the group in the turn tracker
 * @param name the name of the group
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize
public record DdbEncounterGroup(String id, int order, String name) {
}

