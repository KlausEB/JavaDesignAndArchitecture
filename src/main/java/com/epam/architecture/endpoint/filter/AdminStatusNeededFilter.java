package com.epam.architecture.endpoint.filter;

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
@AdminStatusNeeded
@Priority(Priorities.AUTHENTICATION)
public class AdminStatusNeededFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        if (!AuthorizationUtil.isAuthorizeRequest(containerRequestContext, Set.of(RoleEnum.ADMIN.toString()))) {
            containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
