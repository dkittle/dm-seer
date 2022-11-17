package ca.kittle;

import ca.kittle.resources.CharacterResource;
import ca.kittle.resources.HealthResource;
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
                CharacterResource.class,
                HealthResource.class
        );
    }
}
