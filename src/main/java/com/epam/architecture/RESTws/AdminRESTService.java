package com.epam.architecture.RESTws;

import com.epam.architecture.userinterface.LibraryService;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/admin")
public class AdminRESTService {

    private LibraryService libraryService = LibraryService.getInstanceWithDeserializeData();

    @DELETE
    @Path("/addUser/{login}/{password}")
    public Response addNewUser(@PathParam("login") String login, @PathParam("password") String password) {
        return Response.status(Response.Status.CREATED).entity(libraryService.addNewUser(login, password)).build();
    }

    @DELETE
    @Path("/banUser/{login}")
    public Response banUser(@PathParam("login") String login) {
        return Response.status(Response.Status.OK).entity(libraryService.banUser(login)).build();
    }

    @DELETE
    @Path("/history")
    public Response takeHistory() {
        return Response.status(Response.Status.OK).entity(libraryService.takeHistory()).build();
    }
}
