package ca.kittle;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Activates the REST (api) application
 */
@ApplicationPath("/api")
public class RestAPI extends Application {
}
