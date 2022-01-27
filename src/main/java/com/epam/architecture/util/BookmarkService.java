package com.epam.architecture.util;

import com.epam.architecture.datasource.EntityTypes;
import com.epam.architecture.datasource.HTwoDataSourceService;
import com.epam.architecture.datasource.LibraryDAO;
import com.epam.architecture.entities.Bookmark;
import com.epam.architecture.userinterface.LibraryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookmarkService {
    public static final String PATH_TO_JSON_FILE = LibraryService.properties.getProperty("bookmarkDataSource");
    private final LibraryDAO<Bookmark> dataSourceService = new HTwoDataSourceService<>(EntityTypes.BOOKMARK);
    private List<Bookmark> bookmarksList = new ArrayList<>();

    public boolean appendBookmark(String userLogin, String isbn, int pageName) {
        Bookmark newBookmark = createBookmark(userLogin, isbn, pageName);
        dataSourceService.saveData(newBookmark);
        return bookmarksList.add(newBookmark);
    }

    public Bookmark createBookmark(String userLogin, String isbn, int numberOfPage) {
        return new Bookmark(userLogin, isbn, numberOfPage);
    }

    public List<Bookmark> getBookmarks(String login) {
        return bookmarksList.stream()
                .filter(x -> x.getUserLogin().equals(login))
                .collect(Collectors.toList());
    }

    public boolean deleteBookmark(String userLogin, String isbn, int pageNumber) {
        return bookmarksList
                .removeIf(x -> {
                    if (x.getUserLogin().equals(userLogin)
                            && x.getIsbn().equals(isbn)
                            && x.getPageNumber() == pageNumber) {
                        dataSourceService.deleteData(x);
                        return true;
                    }
                    return false;
                });
    }

    public void deleteAllUserBookmarks(String userLogin) {
        List<Bookmark> deletedBookmarks = new ArrayList<>();
        if (bookmarksList
                .removeIf(x -> {
                    if (x.getUserLogin().equals(userLogin)) {
                        deletedBookmarks.add(x);
                        return true;
                    }
                    return false;
                })) {
            dataSourceService.deleteData(deletedBookmarks.toArray(Bookmark[]::new));
        }
    }

    public void deleteAllBookBookmarks(String isbn) {
        List<Bookmark> deletedBookmarks = new ArrayList<>();
        if (bookmarksList
                .removeIf(x -> {
                    if (x.getIsbn().equals(isbn)) {
                        deletedBookmarks.add(x);
                        return true;
                    }
                    return false;
                })) {
            dataSourceService.deleteData(deletedBookmarks.toArray(Bookmark[]::new));
        }
    }

    public Set<String> takeBooksWithCurrentUserBookmarks(String login) {
        return bookmarksList.stream()
                .filter(x -> x.getUserLogin().equals(login))
                .map(Bookmark::getIsbn)
                .collect(Collectors.toSet());
    }

    public void saveBookmarkData() {
        Bookmark[] bookmarks = bookmarksList.toArray(new Bookmark[0]);
        dataSourceService.saveData(bookmarks);
    }

    public void loadBookmarkData() {
        bookmarksList = dataSourceService.restoreData();
    }
}
