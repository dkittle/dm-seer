package ca.kittle.resources;

import ca.kittle.models.app.Campaign;
import ca.kittle.models.app.Creature;
import ca.kittle.repositories.CampaignRepository;
import ca.kittle.repositories.CreatureRepository;
import ca.kittle.repositories.NotFoundException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/creature")
public class CreatureResource {
    private static final Logger logger = LoggerFactory.getLogger(CreatureResource.class);

    private final CreatureRepository creatures = new CreatureRepository();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response creature(@PathParam("id") final long id) {
        logger.debug("Get creature");
        var creature = creatures.creature(id);
        if (creature.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).entity("Creature "+ id + " not found.").build();
        return Response.ok(creature.get()).build();
    }

//    @GET
//    @Path("")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response creature(@QueryParam("name") final String name) {
//        logger.debug("Get creature by name");
//        Campaign campaign = null;
//        try {
//            campaign = campaigns.campaignByName(name);
//        }
//        catch (NotFoundException e) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        return Response.ok(campaign).build();
//    }

}
