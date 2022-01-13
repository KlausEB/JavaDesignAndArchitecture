package com.epam.architecture.webapp.commands;

import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.webapp.util.LibraryWebWorker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AppendAuthorCommand implements WebCommand {
    public static String APPEND_AUTHOR_PAGE = "jsp/appendAuthor.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authorName = request.getParameter("authorName");
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        if (libraryService.appendAuthor(authorName)) {
            request.setAttribute("result", "The addition was successful");
        }
        request.getRequestDispatcher(APPEND_AUTHOR_PAGE).forward(request, response);
    }
}
