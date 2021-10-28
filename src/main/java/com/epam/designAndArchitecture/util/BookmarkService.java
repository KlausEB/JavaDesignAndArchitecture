package com.epam.designAndArchitecture.util;

import com.epam.designAndArchitecture.entities.Book;
import com.epam.designAndArchitecture.entities.Bookmark;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookmarkService {
    private List<Bookmark> currentUserBookmarks;

    public boolean appendBookmark(Book book, int pageName) {
        Bookmark newBookmark = createBookmark(book, pageName);
        return currentUserBookmarks.add(newBookmark);
    }

    public Bookmark createBookmark(Book book, int numberOfPage) {
        return new Bookmark(book, numberOfPage);
    }

    public List<Bookmark> getCurrentUserBookmarks() {
        return currentUserBookmarks;
    }

    public void setCurrentUserBookmarks(List<Bookmark> currentUserBookmarks) {
        this.currentUserBookmarks = currentUserBookmarks;
    }

    public boolean deleteBookmark(Book bookByISBN, int pageNumber) {
        Bookmark newBookmark = createBookmark(bookByISBN, pageNumber);
        return currentUserBookmarks.remove(newBookmark);
    }

    public Set<Book> takeBooksWithBookmarks() {
        Set<Book> booksWithBookmarks = new HashSet<>();
        for (Bookmark currentBookmark : currentUserBookmarks) {
            Book currentBook = currentBookmark.getBook();
            booksWithBookmarks.add(currentBook);
        }
        return booksWithBookmarks;
    }
}
