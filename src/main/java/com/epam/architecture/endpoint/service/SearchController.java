package com.epam.architecture.endpoint.service;

import com.epam.architecture.userinterface.LibraryService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/search")
public class SearchController {

    private LibraryService libraryService = LibraryService.getInstanceWithDeserializeData();

    @GET
    @Path("/booksByAuthorName/{partName}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response booksByPartAuthorName(@PathParam("partName") String partName) {
        return Response.status(Response.Status.OK).entity(libraryService.booksByPartAuthorName(partName)).build();
    }

    @GET
    @Path("/booksByName/{partName}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response booksByPartName(@PathParam("partName") String partName) {
        return Response.status(Response.Status.OK).entity(libraryService.booksByPartName(partName)).build();
    }

    @GET
    @Path("/booksByIsbn/{isbn}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response bookByISBN(@PathParam("isbn") String isbn) {
        return Response.status(Response.Status.OK).entity(libraryService.bookByISBN(isbn)).build();
    }

    @GET
    @Path("/booksByYearRange")
    @Produces({MediaType.APPLICATION_JSON})
    public Response booksByYearRange(@QueryParam("minYear") int minYear, @QueryParam("maxYear") int maxYear) {
        return Response.status(Response.Status.OK).entity(libraryService.booksByYearRange(minYear, maxYear)).build();
    }

    @GET
    @Path("/booksByYearPagesName")
    @Produces({MediaType.APPLICATION_JSON})
    public Response booksByYearPagesPartName(@QueryParam("year") int yearOfPublishing, @QueryParam("pages") int numberOfPages, @QueryParam("name") String partName) {
        return Response.status(Response.Status.OK).entity(libraryService.booksByYearPagesPartName(yearOfPublishing, numberOfPages, partName)).build();
    }
}
