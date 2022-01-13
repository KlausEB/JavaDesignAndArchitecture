package com.epam.architecture.webapp.commands;

import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.webapp.util.LibraryWebWorker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AppendBookCommand implements WebCommand {
    public static String APPEND_BOOK_PAGE = "jsp/appendBook.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookISBN = request.getParameter("bookISBN");
        String authorName = request.getParameter("authorName");
        String bookName = request.getParameter("bookName");
        int yearOfPublishing = Integer.parseInt(request.getParameter("yearOfPublishing"));
        int numberOfPages = Integer.parseInt(request.getParameter("numberOfPages"));
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        if (libraryService.appendBook(authorName, bookName, yearOfPublishing, numberOfPages, bookISBN)) {
            request.setAttribute("result", "The addition was successful");
        }
        request.getRequestDispatcher(APPEND_BOOK_PAGE).forward(request, response);
    }
}
