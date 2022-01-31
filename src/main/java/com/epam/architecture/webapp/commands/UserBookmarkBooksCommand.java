package com.epam.architecture.webapp.commands;

import com.epam.architecture.entities.Book;
import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.webapp.util.LibraryWebWorker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserBookmarkBooksCommand implements WebCommand {
    public static String SHOW_USER_BOOKMARK_BOOKS_PAGE = "jsp/searchUserBookmarkBooks.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getSession().getAttribute(CookieCommand.LOGIN_NAME);
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        List<Book> booksWithUserBookmarks = libraryService.booksWithUserBookmarks(login);
        request.setAttribute("booksList", booksWithUserBookmarks);
        request.getRequestDispatcher(SHOW_USER_BOOKMARK_BOOKS_PAGE).forward(request, response);
    }
}
