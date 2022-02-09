package com.epam.architecture.RESTws.service;

import com.epam.architecture.RESTws.DTO.BookDTO;
import com.epam.architecture.RESTws.filter.JWTTokenNeeded;
import com.epam.architecture.userinterface.LibraryService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/book")
@JWTTokenNeeded
public class BookController {

    private LibraryService libraryService = LibraryService.getInstanceWithDeserializeData();

    @POST
    @Path("/add")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addBook(BookDTO book) {
        return Response.status(Response.Status.CREATED).entity(libraryService.appendBook(
                book.getAuthorName(),
                book.getBookName(),
                book.getYearOfPublishing(),
                book.getNumberOfPages(),
                book.getBookISBN())).build();
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
