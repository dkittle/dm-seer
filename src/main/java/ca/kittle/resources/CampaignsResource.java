package ca.kittle.resources;

import ca.kittle.repositories.CampaignRepository;
import ca.kittle.services.CampaignsService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/campaigns")
public class CampaignsResource {
    private static final Logger logger = LoggerFactory.getLogger(CampaignsResource.class);

    private final CampaignsService campaigns = new CampaignsService();

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response activeCampaigns() {
        logger.debug("Getting active campaigns");
        return Response.ok(campaigns.activeCampaigns()).build();
    }
}



