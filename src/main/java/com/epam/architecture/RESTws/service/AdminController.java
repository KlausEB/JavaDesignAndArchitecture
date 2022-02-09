package com.epam.architecture.RESTws.service;

import com.epam.architecture.RESTws.DTO.UserDTO;
import com.epam.architecture.RESTws.filter.AdminStatusNeeded;
import com.epam.architecture.RESTws.filter.JWTTokenNeeded;
import com.epam.architecture.userinterface.LibraryService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/admin")
@JWTTokenNeeded
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
