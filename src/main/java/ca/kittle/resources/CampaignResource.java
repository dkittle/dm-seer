package ca.kittle.resources;

import ca.kittle.services.CampaigsnService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/campaigns")
public class CampaignResource {
    private static final Logger logger = LoggerFactory.getLogger(CampaignResource.class);

    private final CampaigsnService campaigns = new CampaigsnService();

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response activeCampaigns() {
        logger.debug("Getting active campaigns");
        return Response.ok(campaigns.activeCampaigns()).build();
    }
}



