package ca.kittle.services;

import ca.kittle.integrations.DdbProxy;

public class CampaignsService {

    private final DdbProxy ddbProxy = new DdbProxy();

    public String activeCampaigns() {
        return ddbProxy.getActiveCampaigns();
    }

}
