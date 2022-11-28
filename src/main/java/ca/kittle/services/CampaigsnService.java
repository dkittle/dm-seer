package ca.kittle.services;

import ca.kittle.integrations.DdbProxy;
import ca.kittle.models.integrations.DdbCharacter;

public class CampaigsnService {

    private final DdbProxy ddbProxy = new DdbProxy();

    public String activeCampaigns() {
        return ddbProxy.getActiveCampaigns();
    }

}
