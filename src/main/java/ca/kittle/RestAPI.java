package ca.kittle;

import ca.kittle.resources.*;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.Set;

/**
 * Activates the REST (api) application
 */
@ApplicationPath("/api")
public class RestAPI extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(
                EncountersResource.class,
                EncounterResource.class,
                CharacterResource.class,
                CampaignResource.class,
                HealthResource.class
        );
    }
}
