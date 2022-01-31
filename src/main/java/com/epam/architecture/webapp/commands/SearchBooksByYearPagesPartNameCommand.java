package com.epam.architecture.webapp.commands;

import com.epam.architecture.entities.Book;
import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.webapp.util.LibraryWebWorker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchBooksByYearPagesPartNameCommand implements WebCommand {
    public static String SHOW_BOOKS_BY_YEAR_PAGES_NAME_PAGE = "jsp/searchBooksByYearPagesName.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int yearOfPublishing = Integer.parseInt(request.getParameter("yearOfPublishing"));
        int numberOfPages = Integer.parseInt(request.getParameter("numberOfPages"));
        String bookName = request.getParameter("bookName");
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        List<Book> booksByYearPagesName = libraryService.booksByYearPagesPartName(yearOfPublishing, numberOfPages, bookName);
        request.setAttribute("booksList", booksByYearPagesName);
        request.getRequestDispatcher(SHOW_BOOKS_BY_YEAR_PAGES_NAME_PAGE).forward(request, response);
    }
}
