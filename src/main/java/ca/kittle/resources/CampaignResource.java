package ca.kittle.resources;

import ca.kittle.models.app.Campaign;
import ca.kittle.repositories.CampaignRepository;
import ca.kittle.repositories.NotFoundException;
import ca.kittle.services.CampaignsService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/campaign")
public class CampaignResource {
    private static final Logger logger = LoggerFactory.getLogger(CampaignResource.class);

    private final CampaignRepository campaigns = new CampaignRepository();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response campaign(@PathParam("id") final long id) {
        logger.debug("Get campaign");
        var campaign = campaigns.campaign(id);
        if (campaign.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).entity("Campaign " + id + " not found.").build();
        return Response.ok(campaign.get()).build();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response campaign(@QueryParam("name") final String name) {
        logger.debug("Get campaign by name");
        var campaign = campaigns.campaignByName(name);
        if (campaign.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).entity("Campaign '" + name + "' not found.").build();
        return Response.ok(campaign.get()).build();
    }
}



