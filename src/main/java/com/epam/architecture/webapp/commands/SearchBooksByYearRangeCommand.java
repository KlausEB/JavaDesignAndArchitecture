package com.epam.architecture.webapp.commands;

import com.epam.architecture.entities.Book;
import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.webapp.util.LibraryWebWorker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchBooksByYearRangeCommand implements WebCommand {
    public static String SHOW_BOOKS_BY_YEAR_PAGE = "jsp/searchBooksByYearRange.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int minYear = Integer.parseInt(request.getParameter("minYear"));
        int maxYear = Integer.parseInt(request.getParameter("maxYear"));
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        List<Book> booksByYearRange = libraryService.booksByYearRange(minYear, maxYear);
        request.setAttribute("booksList", booksByYearRange);
        request.getRequestDispatcher(SHOW_BOOKS_BY_YEAR_PAGE).forward(request, response);
    }
}
