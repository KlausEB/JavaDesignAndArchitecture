package com.epam.architecture.webapp.commands;

import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.webapp.util.LibraryWebWorker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteBookCommand implements WebCommand {
    public static String DELETE_BOOK_PAGE = "jsp/deleteBook.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookName = request.getParameter("bookName");
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        if (libraryService.deleteBook(bookName)) {
            request.setAttribute("result", "The deletion was successful");
        }
        request.getRequestDispatcher(DELETE_BOOK_PAGE).forward(request, response);
    }
}
