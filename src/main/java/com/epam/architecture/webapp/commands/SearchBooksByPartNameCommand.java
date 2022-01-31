package com.epam.architecture.webapp.commands;

import com.epam.architecture.entities.Book;
import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.webapp.util.LibraryWebWorker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchBooksByPartNameCommand implements WebCommand {
    public static String SHOW_BOOKS_BY_NAME_PAGE = "jsp/searchBooksByName.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookName = request.getParameter("bookName");
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        List<Book> booksByName = libraryService.booksByPartName(bookName);
        request.setAttribute("booksList", booksByName);
        request.getRequestDispatcher(SHOW_BOOKS_BY_NAME_PAGE).forward(request, response);
    }
}
