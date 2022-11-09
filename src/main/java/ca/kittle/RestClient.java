package ca.kittle;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

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
        logger.info("Connecting to {}", baseUrl);
        target = client.target(baseUrl);
        Invocation.Builder request = target.request();
        try {
            logger.info("Building response");
            Response response = request.get();
            logger.info("Response status {}", response.getStatusInfo());
//            disconnect(response);
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
