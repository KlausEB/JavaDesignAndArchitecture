package com.epam.architecture.webapp.commands;

import com.epam.architecture.entities.Book;
import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.webapp.util.LibraryWebWorker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchBooksByPartAuthorCommand implements WebCommand {
    public static String SHOW_BOOKS_BY_AUTHOR_PAGE = "jsp/searchBooksByAuthor.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authorName = request.getParameter("authorName");
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        List<Book> booksByAuthor = libraryService.booksByPartAuthorName(authorName);
        request.setAttribute("booksList", booksByAuthor);
        request.getRequestDispatcher(SHOW_BOOKS_BY_AUTHOR_PAGE).forward(request, response);
    }
}
