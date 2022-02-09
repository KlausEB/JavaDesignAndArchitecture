package com.epam.architecture.RESTws.service;

import com.epam.architecture.RESTws.DTO.BookmarkDTO;
import com.epam.architecture.RESTws.DTO.UserDTO;
import com.epam.architecture.RESTws.filter.JWTTokenNeeded;
import com.epam.architecture.roles.AuthorizationUtil;
import com.epam.architecture.userinterface.LibraryService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
@JWTTokenNeeded
public class UserController {

    private LibraryService libraryService = LibraryService.getInstanceWithDeserializeData();

    @POST
    @Path("/authorization")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response authenticateUser(UserDTO user) {
       return AuthorizationUtil.authorizationRequest(user);
    }

    @POST
    @Path("/add")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addBookmark(BookmarkDTO bookmark) {
        return Response.status(Response.Status.CREATED).entity(libraryService.addBookmark(AuthorizationUtil.getLoginFromRequest(context), bookmark.getIsbn(), bookmark.getPageNumber())).build();
    }

    @DELETE
    @Path("/deleteBookmark")
    public Response deleteBookmark(@QueryParam("isbn") String isbn, @QueryParam("pageNumber") int pageNumber) {
        return Response.status(Response.Status.OK).entity(libraryService.deleteBookmark(AuthorizationUtil.getLoginFromRequest(context), isbn, pageNumber)).build();
    }

    @GET
    @Path("/bookmarks")
    @Produces({MediaType.APPLICATION_JSON})
    public Response booksWithUserBookmarks() {
        return Response.status(Response.Status.OK).entity(libraryService.booksWithUserBookmarks(AuthorizationUtil.getLoginFromRequest(context))).build();
    }
}
