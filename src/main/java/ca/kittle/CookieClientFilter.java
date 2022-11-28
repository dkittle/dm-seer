package ca.kittle;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.core.Cookie;

import java.util.ArrayList;
import java.util.List;

public class CookieClientFilter implements ClientRequestFilter {

    private List<Object> cookies = new ArrayList<>();

    public CookieClientFilter(Cookie cookie) {
        super();
        this.cookies.add(cookie);
    }

    public CookieClientFilter(List<Cookie> list) {
        this.cookies.addAll(list);
    }

    @Override
    public void filter(ClientRequestContext clientRequestContext) {
//        clientRequestContext.getCookies().entrySet().forEach(item -> cookies.add(item.getValue()));
//        clientRequestContext.getHeaders().put("Cookie", cookies);
    }
}
