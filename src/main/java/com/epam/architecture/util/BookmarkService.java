package com.epam.architecture.util;

import com.epam.architecture.App;
import com.epam.architecture.datasource.DataSourceService;
import com.epam.architecture.datasource.DataSourceType;
import com.epam.architecture.entities.Bookmark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookmarkService {
    public static final String PATH_TO_JSON_FILE = App.properties.getProperty("bookmarkDataSource");
    private final DataSourceService dataSourceService = new DataSourceService(PATH_TO_JSON_FILE, DataSourceType.BOOKMARK);
    private List<Bookmark> bookmarksList = new ArrayList<>();

    public boolean appendBookmark(String userLogin, String isbn, int pageName) {
        Bookmark newBookmark = createBookmark(userLogin, isbn, pageName);
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
                .removeIf(x -> (x.getUserLogin().equals(userLogin)
                        && x.getIsbn().equals(isbn)
                        && x.getPageNumber() == pageNumber));
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
        bookmarksList = new ArrayList<>();
        Bookmark[] bookmarks = (Bookmark[]) dataSourceService.restoreData();
        bookmarksList.addAll(Arrays.asList(bookmarks));
    }
}
