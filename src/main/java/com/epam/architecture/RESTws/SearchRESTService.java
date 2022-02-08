package com.epam.architecture.RESTws;

import com.epam.architecture.userinterface.LibraryService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/search")
public class SearchRESTService {

    private LibraryService libraryService = LibraryService.getInstanceWithDeserializeData();

    @GET
    @Path("/booksByAuthorName/{partName}")
//    @Produces({MediaType.APPLICATION_JSON})
    public Response booksByPartAuthorName(@PathParam("partName") String partName) {
        return Response.status(Response.Status.OK).entity(libraryService.booksByPartAuthorName(partName)).build();
    }

    @GET
    @Path("/booksByName/{partName}")
//    @Produces({MediaType.APPLICATION_JSON})
    public Response booksByPartName(@PathParam("partName") String partName) {
        return Response.status(Response.Status.OK).entity(libraryService.booksByPartName(partName)).build();
    }

    @GET
    @Path("/booksByIsbn/{isbn}")
//    @Produces({MediaType.APPLICATION_JSON})
    public Response bookByISBN(@PathParam("isbn") String isbn) {
        return Response.status(Response.Status.OK).entity(libraryService.bookByISBN(isbn)).build();
    }

    @GET
    @Path("/booksByYearRange/{minYear: \\d+}/{maxYear: \\d+}")
//    @Produces({MediaType.APPLICATION_JSON})
    public Response booksByYearRange(@PathParam("minYear") int minYear, @PathParam("maxYear") int maxYear) {
        return Response.status(Response.Status.OK).entity(libraryService.booksByYearRange(minYear, maxYear)).build();
    }

    @GET
    @Path("/booksByYearPagesName/{year: \\d+}/{pages: \\d+}/{name}")
//    @Produces({MediaType.APPLICATION_JSON})
    public Response booksByYearPagesPartName(@PathParam("year") int yearOfPublishing, @PathParam("pages") int numberOfPages, @PathParam("name") String partName) {
        return Response.status(Response.Status.OK).entity(libraryService.booksByYearPagesPartName(yearOfPublishing, numberOfPages, partName)).build();
    }
}
