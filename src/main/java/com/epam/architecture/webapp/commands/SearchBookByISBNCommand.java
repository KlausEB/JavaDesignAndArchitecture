package com.epam.architecture.webapp.commands;

import com.epam.architecture.entities.Book;
import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.webapp.util.LibraryWebWorker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchBookByISBNCommand implements WebCommand {
    public static String SHOW_BOOK_BY_ISBN_PAGE = "jsp/searchBookByISBN.jsp";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookISBN = request.getParameter("bookISBN");
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        Book bookByISBN = libraryService.bookByISBN(bookISBN);
        request.setAttribute("book", bookByISBN);
        request.getRequestDispatcher(SHOW_BOOK_BY_ISBN_PAGE).forward(request, response);
    }
}
