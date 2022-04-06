package com.epam.architecture.service;

import com.epam.architecture.model.Book;
import com.epam.architecture.model.Bookmark;

import java.util.List;

public interface BookmarkService {
    void addBookmark(String userLogin, String isbn, int pageName);

    Bookmark createBookmark(String userLogin, String isbn, int numberOfPage);

    void deleteBookmark(String userLogin, String isbn, int pageNumber);

    List<Book> takeBooksWithUserBookmarks(String login);
}
