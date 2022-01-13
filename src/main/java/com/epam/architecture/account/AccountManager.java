package com.epam.architecture.account;


import com.epam.architecture.datasource.EntityTypes;
import com.epam.architecture.datasource.HTwoDataSourceService;
import com.epam.architecture.datasource.LibraryDAO;
import com.epam.architecture.entities.User;
import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.util.BookmarkService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AccountManager {
    public static final String PATH_TO_JSON_FILE = LibraryService.properties.getProperty("accountDataSource");
    private final LibraryDAO<User> dataSourceService = new HTwoDataSourceService<>(EntityTypes.ACCOUNT);
    private Map<String, User> userMap = new HashMap<>();
    private BookmarkService bookmarkService = new BookmarkService();

    public User createUser(String login, String password) {
        return new User(login, password);
    }

    public boolean logInUser(String login, String password) {
        User userForSearch = createUser(login, password);
        User searchedUser = userMap.get(login);
        return userForSearch.equals(searchedUser);
    }

    public boolean signUpUser(String login, String password) {
        User newUser = appendAccount(login, password);
        if (newUser == null) {
            return false;
        }
        dataSourceService.saveData(newUser);
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

    public boolean appendAdminAccount(String adminLogin, String newLogin, String password) {
        if (userIsAdmin(adminLogin)) {
            return false;
        }
        return appendAccount(newLogin, password) != null;
    }

    public boolean deleteUser(String adminLogin, String deleteLogin) {
        if (userIsAdmin(adminLogin)) {
            return false;
        }
        User deletedUser = userMap.remove(deleteLogin);
        if (deletedUser != null) {
            dataSourceService.deleteData(deletedUser);
            bookmarkService.deleteAllUserBookmarks(deleteLogin);
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

    public boolean appendBookmark(String login, String isbn, int pageNumber) {
        return bookmarkService.appendBookmark(login, isbn, pageNumber);
    }

    public boolean deleteBookmark(String login, String isbn, int pageNumber) {
        return bookmarkService.deleteBookmark(login, isbn, pageNumber);
    }

    public void deleteAllBookBookmarks(String isbn) {
        bookmarkService.deleteAllBookBookmarks(isbn);
    }

    public Set<String> takeBooksWithUserBookmarks(String login) {
        return bookmarkService.takeBooksWithCurrentUserBookmarks(login);
    }

    public BookmarkService getBookmarkService() {
        return bookmarkService;
    }

    public void setBookmarkService(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    public boolean userIsAdmin(String login) {
        return userMap.get(login).isAdminRights();
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<String, User> userMap) {
        this.userMap = userMap;
    }
}
