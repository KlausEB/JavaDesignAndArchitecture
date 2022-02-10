package com.epam.architecture.RESTws.filter;

import com.epam.architecture.roles.AuthorizationUtil;
import com.epam.architecture.roles.RoleEnum;
import jakarta.annotation.Priority;

import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Set;

@Provider
@UserStatusNeeded
@Priority(Priorities.AUTHENTICATION)
public class UserStatusNeededFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (!AuthorizationUtil.isAuthorizeRequest(requestContext, Set.of(RoleEnum.ADMIN, RoleEnum.USER))) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
