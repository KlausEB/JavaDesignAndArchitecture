package com.epam.architecture.endpoint.service;

import com.epam.architecture.endpoint.dto.BookmarkDTO;
import com.epam.architecture.endpoint.dto.UserDTO;
import com.epam.architecture.endpoint.filter.UserStatusNeeded;
import com.epam.architecture.roles.AuthorizationUtil;
import com.epam.architecture.userinterface.LibraryService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserController {
    @Context
    private HttpHeaders context;

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
    @UserStatusNeeded
    public Response addBookmark(BookmarkDTO bookmark) {
        return Response.status(Response.Status.CREATED).entity(libraryService.addBookmark(AuthorizationUtil.getLoginFromRequest(context), bookmark.getIsbn(), bookmark.getPageNumber())).build();
    }

    @DELETE
    @Path("/deleteBookmark")
    @UserStatusNeeded
    public Response deleteBookmark(@QueryParam("isbn") String isbn, @QueryParam("pageNumber") int pageNumber) {
        return Response.status(Response.Status.OK).entity(libraryService.deleteBookmark(AuthorizationUtil.getLoginFromRequest(context), isbn, pageNumber)).build();
    }

    @GET
    @Path("/bookmarks")
    @Produces({MediaType.APPLICATION_JSON})
    @UserStatusNeeded
    public Response booksWithUserBookmarks() {
        return Response.status(Response.Status.OK).entity(libraryService.booksWithUserBookmarks(AuthorizationUtil.getLoginFromRequest(context))).build();
    }

    @POST
    @Path("/save")
    @UserStatusNeeded
    public Response addBookmark() {
        libraryService.requestSerializeData();
        return Response.status(Response.Status.OK).build();
    }
}
