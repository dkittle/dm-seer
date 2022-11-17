package ca.kittle.resources;

import ca.kittle.models.Health;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/health")
public class HealthResource {

    private static final Logger logger = LoggerFactory.getLogger(HealthResource.class);

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHealth() {
        logger.info("Getting application health");
        return Response.ok(Health.OK).build();
    }

}
