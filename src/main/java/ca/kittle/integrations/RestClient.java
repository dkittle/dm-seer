package ca.kittle.integrations;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestClient {

    private static Logger logger = LoggerFactory.getLogger(RestClient.class);
    private String baseUrl = "";
    private ResteasyClient client;
    private ResteasyWebTarget target;

    public RestClient(String baseUrl) {
        logger.info("Building rest client for {}", baseUrl);
        assert baseUrl != null && !baseUrl.isEmpty();

        this.baseUrl = baseUrl;
    }

    public Response connect(){
        logger.info("Connecting");
        client = (ResteasyClient)ClientBuilder.newClient();
        client.register(ResteasyJackson2Provider.class);
        logger.info("Connecting to {}", baseUrl);
        target = client.target(baseUrl);
        Invocation.Builder request = target.request();
        try {
            logger.info("Building response");
            Response response = request.get();
            logger.info("Response status {}", response.getStatusInfo());
            return response;
        }
        catch (RuntimeException e) {
            logger.error("Could not connect to " + baseUrl, e);
        }
        finally {
//            disconnect(null);
        }
        return null;
    }

    public void disconnect(Response response) {
        logger.info("Disconnecting from {}", baseUrl);
        if (response != null) {
            response.close();
        }
        client.close();
    }
}
