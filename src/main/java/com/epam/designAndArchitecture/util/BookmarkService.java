package com.epam.designAndArchitecture.util;

import com.epam.designAndArchitecture.entities.Bookmark;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookmarkService {
    private List<Bookmark> currentUserBookmarks;

    public boolean appendBookmark(String ISBN, int pageName) {
        Bookmark newBookmark = createBookmark(ISBN, pageName);
        return currentUserBookmarks.add(newBookmark);
    }

    public Bookmark createBookmark(String ISBN, int numberOfPage) {
        return new Bookmark(ISBN, numberOfPage);
    }

    public List<Bookmark> getCurrentUserBookmarks() {
        return currentUserBookmarks;
    }

    public void setCurrentUserBookmarks(List<Bookmark> currentUserBookmarks) {
        this.currentUserBookmarks = currentUserBookmarks;
    }

    public boolean deleteBookmark(String ISBN, int pageNumber) {
        Bookmark newBookmark = createBookmark(ISBN, pageNumber);
        return currentUserBookmarks.remove(newBookmark);
    }

    public Set<String> takeBooksWithBookmarks() {
        Set<String> ISBNBooksWithBookmarks = new HashSet<>();
        for (Bookmark currentBookmark : currentUserBookmarks) {
            String currentBook = currentBookmark.getISBN();
            ISBNBooksWithBookmarks.add(currentBook);
        }
        return ISBNBooksWithBookmarks;
    }
}
