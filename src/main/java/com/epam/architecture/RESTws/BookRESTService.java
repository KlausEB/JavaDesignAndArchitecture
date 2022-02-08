package com.epam.architecture.RESTws;

import com.epam.architecture.userinterface.LibraryService;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/book")
public class BookRESTService {

    private LibraryService libraryService = LibraryService.getInstanceWithDeserializeData();

    @POST
    @Path("/add/{authorName}/{bookName}/{yearOfPublishing}/{numberOfPages}/{bookISBN}")
    public Response addBook(@PathParam("authorName") String authorName,
                            @PathParam("bookName") String bookName,
                            @PathParam("yearOfPublishing") int yearOfPublishing,
                            @PathParam("numberOfPages") int numberOfPages,
                            @PathParam("bookISBN") String bookISBN) {
        return Response.status(Response.Status.CREATED).entity(libraryService.appendBook(authorName, bookName, yearOfPublishing, numberOfPages, bookISBN)).build();
    }

    @DELETE
    @Path("/delete/{bookISBN}")
    public Response deleteBook(@PathParam("bookISBN") String bookISBN) {
        return Response.status(Response.Status.OK).entity(libraryService.deleteBook(bookISBN)).build();
    }

    @POST
    @Path("/addAuthor/{authorName}")
    public Response addAuthor(@PathParam("authorName") String authorName) {
        return Response.status(Response.Status.CREATED).entity(libraryService.appendAuthor(authorName)).build();
    }

    @DELETE
    @Path("/delete/{authorName}")
    public Response deleteAuthor(@PathParam("authorName") String authorName) {
        return Response.status(Response.Status.OK).entity(libraryService.deleteAuthor(authorName)).build();
    }
}
