package ca.kittle;

import ca.kittle.services.CharacterService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/character")
public class CharacterResource {


    private CharacterService character = new CharacterService();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCharacter(@PathParam("id") final String id) {
        return Response.ok(character.getCharacter(id)).build();
    }
}
