package com.epam.architecture.RESTws;

import com.epam.architecture.roles.AuthorizationUtil;
import com.epam.architecture.userinterface.LibraryService;
import jakarta.annotation.Resource;
import jakarta.xml.ws.WebServiceContext;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserRESTService {

    @Resource
    private WebServiceContext context;

    private LibraryService libraryService = LibraryService.getInstanceWithDeserializeData();

    @POST
    @Path("/addBookmark/{isbn}/{pageNumber: \\d+}")
    public Response addBookmark(@PathParam("isbn") String isbn, @PathParam("pageNumber") int pageNumber) {
        return Response.status(Response.Status.CREATED).entity(libraryService.addBookmark(AuthorizationUtil.getLoginFromRequest(context), isbn, pageNumber)).build();
    }

    @DELETE
    @Path("/deleteBookmark/{isbn}/{pageNumber: \\d+}")
    public Response deleteBookmark(@PathParam("isbn") String isbn, @PathParam("pageNumber") int pageNumber) {
        return Response.status(Response.Status.OK).entity(libraryService.deleteBookmark(AuthorizationUtil.getLoginFromRequest(context), isbn, pageNumber)).build();
    }

    @GET
    @Path("/bookmarks")
//    @Produces({MediaType.APPLICATION_JSON})
    public Response booksWithUserBookmarks() {
        return Response.status(Response.Status.OK).entity(libraryService.booksWithUserBookmarks(AuthorizationUtil.getLoginFromRequest(context))).build();
    }
}
