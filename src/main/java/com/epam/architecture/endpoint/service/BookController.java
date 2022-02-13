package com.epam.architecture.endpoint.service;

import com.epam.architecture.endpoint.dto.BookDTO;
import com.epam.architecture.endpoint.filter.UserStatusNeeded;
import com.epam.architecture.userinterface.LibraryService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/book")
@UserStatusNeeded
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
    @Path("/deleteAuthor/{authorName}")
    public Response deleteAuthor(@PathParam("authorName") String authorName) {
        return Response.status(Response.Status.OK).entity(libraryService.deleteAuthor(authorName)).build();
    }
}
