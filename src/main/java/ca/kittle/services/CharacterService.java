package ca.kittle.services;

import ca.kittle.integrations.DdbProxy;
import ca.kittle.models.DdbCharacter;
import jakarta.validation.constraints.NotNull;

public class CharacterService {

    private DdbProxy ddbProxy = new DdbProxy();

    public DdbCharacter getCharacter(@NotNull String id) {
        return ddbProxy.getCharacter(id);
    }

}
