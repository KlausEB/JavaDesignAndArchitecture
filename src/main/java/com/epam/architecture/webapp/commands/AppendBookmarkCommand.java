package com.epam.architecture.webapp.commands;

import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.webapp.util.LibraryWebWorker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AppendBookmarkCommand implements WebCommand {
    public static String APPEND_BOOKMARK_PAGE = "jsp/appendBookmark.jsp`";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("bookISBN");
        String login = (String) request.getSession().getAttribute(CookieCommand.LOGIN_NAME);
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        if (libraryService.appendBookmark(login, isbn, pageNumber)) {
            request.setAttribute("result", "The addition was successful");
        }
        request.getRequestDispatcher(APPEND_BOOKMARK_PAGE).forward(request, response);
    }
}
