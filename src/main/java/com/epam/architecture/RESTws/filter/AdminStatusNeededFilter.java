package com.epam.architecture.RESTws.filter;

import jakarta.annotation.Priority;

import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@AdminStatusNeeded
@Priority(Priorities.AUTHENTICATION)
public class AdminStatusNeededFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
    }
}
