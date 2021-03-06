package com.epam.architecture.account;


import com.epam.architecture.App;
import com.epam.architecture.datasource.EntityTypes;
import com.epam.architecture.datasource.HTwoDataSourceService;
import com.epam.architecture.datasource.LibraryDAO;
import com.epam.architecture.entities.User;
import com.epam.architecture.util.BookmarkService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AccountManager {
    public static final String PATH_TO_JSON_FILE = App.properties.getProperty("accountDataSource");
    private final LibraryDAO<User> dataSourceService = new HTwoDataSourceService<>(EntityTypes.ACCOUNT);
    private Map<String, User> userMap = new HashMap<>();
    private User currentUser;
    private BookmarkService bookmarkService = new BookmarkService();

    public User createUser(String login, String password) {
        return new User(login, password);
    }

    public boolean logInUser(String login, String password) {
        User userForSearch = createUser(login, password);
        User searchedUser = userMap.get(login);
        if (userForSearch.equals(searchedUser)) {
            currentUser = searchedUser;
            return true;
        }
        return false;
    }

    public boolean signUpUser(String login, String password) {
        User newUser = appendAccount(login, password);
        if (newUser == null) {
            return false;
        }
        dataSourceService.saveData(newUser);
        currentUser = newUser;
        return true;
    }

    private User appendAccount(String login, String password) {
        if (userMap.containsKey(login)) {
            return null;
        }
        User newUser = createUser(login, password);
        userMap.put(login, newUser);
        return newUser;
    }

    public boolean appendAdminAccount(String login, String password) {
        if (!currentUser.isAdminRights()) {
            return false;
        }
        return appendAccount(login, password) != null;
    }

    public boolean deleteUser(String login) {
        if (!currentUser.isAdminRights()) {
            return false;
        }
        User deletedUser = userMap.remove(login);
        if (deletedUser != null) {
            dataSourceService.deleteData(deletedUser);
            bookmarkService.deleteAllUserBookmarks(login);
            return true;
        }
        return false;
    }

    public void saveAccountData() {
        User[] users = userMap.values().toArray(new User[0]);
        dataSourceService.saveData(users);
        bookmarkService.saveBookmarkData();
    }

    public void loadAccountData() {
        List<User> users = dataSourceService.restoreData();
        for (User element : users) {
            userMap.put(element.getLogin(), element);
        }
        bookmarkService.loadBookmarkData();
    }

    public boolean appendBookmark(String isbn, int pageNumber) {
        return bookmarkService.appendBookmark(currentUser.getLogin(), isbn, pageNumber);
    }

    public boolean deleteBookmark(String isbn, int pageNumber) {
        return bookmarkService.deleteBookmark(currentUser.getLogin(), isbn, pageNumber);
    }

    public void deleteAllBookBookmarks(String isbn) {
        bookmarkService.deleteAllBookBookmarks(isbn);
    }

    public Set<String> takeBooksWithCurrentUserBookmarks() {
        return bookmarkService.takeBooksWithCurrentUserBookmarks(currentUser.getLogin());
    }

    public BookmarkService getBookmarkService() {
        return bookmarkService;
    }

    public void setBookmarkService(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    public boolean isAdmin() {
        return currentUser.isAdminRights();
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<String, User> userMap) {
        this.userMap = userMap;
    }
}
