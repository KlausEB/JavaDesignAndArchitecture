package com.epam.architecture.RESTws.filter;

import com.epam.architecture.roles.RoleEnum;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.Priority;

import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Key;

@Provider
@AdminStatusNeeded
@Priority(Priorities.AUTHENTICATION)
public class AdminStatusNeededFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.ES256);
        Cookie jwtCookie = containerRequestContext.getCookies().get("jwt");
        String jwtToken = jwtCookie.getValue();
        try {
            Jwts.parserBuilder().require("role", RoleEnum.ADMIN).setSigningKey(key).build().parseClaimsJws(jwtToken);
        } catch (Exception e) {
            containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
