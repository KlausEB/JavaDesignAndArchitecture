package com.epam.architecture.webapp.commands;

import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.webapp.util.LibraryWebWorker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteAuthorCommand implements WebCommand {
    public static String DELETE_AUTHOR_PAGE = "jsp/deleteAuthor.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authorName = request.getParameter("authorName");
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        if (libraryService.deleteAuthor(authorName)) {
            request.setAttribute("result", "The deletion was successful");
        }
        request.getRequestDispatcher(DELETE_AUTHOR_PAGE).forward(request, response);
    }
}
