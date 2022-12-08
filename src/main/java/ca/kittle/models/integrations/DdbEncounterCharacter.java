package ca.kittle.models.integrations;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize

public record DdbEncounterCharacter(String id,
                                    int count,
                                    int level,
                                    String type,
                                    boolean hidden,
                                    String race,
                                    String gender,
                                    String name,
                                    String userName,
                                    boolean isReady,
                                    String avatarUrl,
                                    String classByLine,
                                    int initiative,
                                    int currentHitPoints,
                                    int temporaryHitPoints,
                                    int maximumHitPoints) {
}
