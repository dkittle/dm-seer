package ca.kittle.services;

import ca.kittle.integrations.DdbProxy;
import ca.kittle.models.integrations.DdbEncounter;

public class EncounterService {

    private final DdbProxy ddbProxy = new DdbProxy();

    public DdbEncounter encounter(String id) {
        return ddbProxy.getEncounter(id);
    }

    public String ddbEncounters() { return ddbProxy.getEncounters(); }
}
