package com.epam.architecture.endpoint.service;

import com.epam.architecture.endpoint.dto.UserDTO;
import com.epam.architecture.endpoint.filter.AdminStatusNeeded;
import com.epam.architecture.userinterface.LibraryService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/admin")
@AdminStatusNeeded
public class AdminController {

    private LibraryService libraryService = LibraryService.getInstanceWithDeserializeData();

    @POST
    @Path("/user")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addNewUser(UserDTO user) {
        return Response.status(Response.Status.CREATED).entity(libraryService.addNewUser(user.getLogin(), user.getPassword())).build();
    }

    @DELETE
    @Path("/user/{login}")
    public Response banUser(@PathParam("login") String login) {
        return Response.status(Response.Status.OK).entity(libraryService.banUser(login)).build();
    }

    @GET
    @Path("/history")
    public Response takeHistory() {
        return Response.status(Response.Status.OK).entity(libraryService.takeHistory()).build();
    }
}
