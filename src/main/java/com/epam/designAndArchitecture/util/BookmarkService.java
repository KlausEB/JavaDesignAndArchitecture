package com.epam.designAndArchitecture.util;

import com.epam.designAndArchitecture.App;
import com.epam.designAndArchitecture.dataSourceServices.DataSourceService;
import com.epam.designAndArchitecture.dataSourceServices.DataSourceType;
import com.epam.designAndArchitecture.entities.Bookmark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookmarkService {
    public static final String pathToJSONFile = App.properties.getProperty("bookmarkDataSource");
    private final DataSourceService dataSourceService = new DataSourceService(pathToJSONFile, DataSourceType.BOOKMARK);
    private List<Bookmark> bookmarksList = new ArrayList<>();

    public boolean appendBookmark(String userLogin, String ISBN, int pageName) {
        Bookmark newBookmark = createBookmark(userLogin, ISBN, pageName);
        return bookmarksList.add(newBookmark);
    }

    public Bookmark createBookmark(String userLogin, String ISBN, int numberOfPage) {
        return new Bookmark(userLogin, ISBN, numberOfPage);
    }

    public List<Bookmark> getBookmarks(String login) {
        return bookmarksList.stream()
                .filter(x -> x.getUserLogin().equals(login))
                .collect(Collectors.toList());
    }

    public boolean deleteBookmark(String userLogin, String ISBN, int pageNumber) {
        return bookmarksList
                .removeIf(x -> (x.getUserLogin().equals(userLogin)
                        && x.getISBN().equals(ISBN)
                        && x.getPageNumber() == pageNumber));
    }

    public Set<String> takeBooksWithBookmarks() {
        return bookmarksList.stream().map(Bookmark::getISBN).collect(Collectors.toSet());
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
