package com.epam.designAndArchitecture.account;


import com.epam.designAndArchitecture.App;
import com.epam.designAndArchitecture.dataSourceServices.DataSourceService;
import com.epam.designAndArchitecture.dataSourceServices.DataSourceType;
import com.epam.designAndArchitecture.entities.User;
import com.epam.designAndArchitecture.util.BookmarkService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AccountManager {
    public static final String pathToJSONFile = App.properties.getProperty("accountDataSource");
    private final DataSourceService dataSourceService = new DataSourceService(pathToJSONFile, DataSourceType.ACCOUNT);
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
        return userMap.remove(login) != null;
    }

    public void saveAccountData() {
        User[] users = userMap.values().toArray(new User[0]);
        dataSourceService.saveData(users);
        bookmarkService.saveBookmarkData();
    }

    public void loadAccountData() {
        User[] users = (User[]) dataSourceService.restoreData();
        for (User currentUser : users) {
            userMap.put(currentUser.getLogin(), currentUser);
        }
        bookmarkService.loadBookmarkData();
    }

    public boolean appendBookmark(String isbn, int pageNumber) {
        return bookmarkService.appendBookmark(currentUser.getLogin(), isbn, pageNumber);
    }

    public boolean deleteBookmark(String isbn, int pageNumber) {
        return bookmarkService.deleteBookmark(currentUser.getLogin(), isbn, pageNumber);
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
