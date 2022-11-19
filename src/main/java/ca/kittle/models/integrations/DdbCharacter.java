package ca.kittle.models.integrations;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize

public record DdbCharacter(
        String id,
        String userId,
        String username,
        boolean isAssignToPlayer,
        String readOnlyUrl,
        String avatarId,
        List<DdbStat> stats
) {
    public int strength() {
        return getStatValue(1);
    }
    public int dexterity() {
        return getStatValue(2);
    }
    public int constitution() {
        return getStatValue(3);
    }
    public int intelligence() {
        return getStatValue(4);
    }
    public int wisdom() {
        return getStatValue(5);
    }
    public int charisma() {
        return getStatValue(6);
    }

    private int getStatValue(int statIndex) {
        try {
            DdbStat str = stats.stream().filter(t -> t.id() == statIndex).toList().get(0);
            return str.value();
        }
        catch (NullPointerException e) {
        }
        return 0;
    }
}


