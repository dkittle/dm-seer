package ca.kittle.models.integrations;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize

public record DdbEncounter(String id,
                           int userId,
                           String name,
                           String map,
                           String room,
                           String source,
                           boolean inProgress,
                           int roundNum,
                           int turnNum,
                           String notes,
                           List<String> manualEntries,
                           int difficulty,
                           long dateCreated,
                           long dateModified,
                           int versionNumber,
                           int status,
                           DdbEncounterCampaign campaign,
                           String flavorText,
                           String description,
                           String rewards,
                           String compendiumLink,
                           List<DdbEncounterMonster> monsters,
                           List<DdbEncounterGroup> groups,
                           List<DdbEncounterCharacter> players
                           ) {
}
