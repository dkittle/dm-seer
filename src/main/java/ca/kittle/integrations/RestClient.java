package ca.kittle.integrations;

import ca.kittle.CookieClientFilter;
import ca.kittle.LoggingFilter;
import io.undertow.util.Cookies;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;

import org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class RestClient {

    private static final Logger logger = LoggerFactory.getLogger(RestClient.class);
    private String baseUrl = "";
    private ResteasyClient client;

    public RestClient(String baseUrl) {
        logger.info("Building rest client for {}", baseUrl);
        assert baseUrl != null && !baseUrl.isEmpty();

        this.baseUrl = baseUrl;
    }

    private Invocation.Builder buildRequest(MultivaluedMap<String, Object> headers, List<Cookie> cookies) {
        client = (ResteasyClient)ClientBuilder.newClient();
        client.register(ResteasyJackson2Provider.class);
//        if (cookies != null)
//            client.register(new CookieClientFilter(cookies));
        client.register(LoggingFilter.class);
        Invocation.Builder request = client.target(baseUrl).request();
        if(headers != null && !headers.isEmpty())
            request.headers(headers);
        if (cookies != null) {
            for (Cookie cookie : cookies)
                request.cookie(cookie);
        }
        return request;
    }

    public Response get() {
        return get(null, null);
    }

    public Response get(MultivaluedMap<String, Object> headers, List<Cookie> cookies) {
        logger.debug("Getting from {}", baseUrl);
        Invocation.Builder request = buildRequest(headers, cookies);
        try {
            Response response = request.get();
            logger.debug("Response status {}", response.getStatus());
            return response;
        }
        catch (RuntimeException e) {
            logger.error("Could not connect to " + baseUrl, e);
        }
        return null;
    }

    public Response post(MultivaluedMap<String, Object> headers, Entity<?> body, List<Cookie> cookies) {
        logger.debug("Posting to {}", baseUrl);
        Invocation.Builder request = buildRequest(headers, cookies);
        try {
            Response response = request.post(body);
            logger.debug("Response status {}", response.getStatus());
            return response;
        }
        catch (RuntimeException e) {
            logger.error("Could not post to " + baseUrl, e);
        }
        return null;
    }

    public void disconnect(Response response) {
        logger.debug("Disconnecting from {}", baseUrl);
        if (response != null) {
            response.close();
        }
        client.close();
    }
}
