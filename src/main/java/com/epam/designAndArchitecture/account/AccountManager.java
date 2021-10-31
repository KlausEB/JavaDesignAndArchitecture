package com.epam.designAndArchitecture.account;


import com.epam.designAndArchitecture.entities.User;
import com.epam.designAndArchitecture.util.BookmarkService;

import java.util.HashMap;
import java.util.Map;

public class AccountManager {
    public static final String pathToJSONFile = "DB/accounts.json";
    private final AccountDBService dbService = new AccountDBService(pathToJSONFile);
    private Map<String, User> userMap = new HashMap<>();
    private User currentUser;
    private BookmarkService currentBookmarks = new BookmarkService();

    public User createUser(String login, String password) {
        return new User(login, password);
    }

    public boolean logInUser(String login, String password) {
        User searchUser = createUser(login, password);
        User tryToSearchUser = userMap.get(login);
        if (searchUser.equals(tryToSearchUser)) {
            currentUser = tryToSearchUser;
            currentBookmarks.setCurrentUserBookmarks(currentUser.getBookmarkList());
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
        userMap.put(login, currentUser);
        currentBookmarks.setCurrentUserBookmarks(currentUser.getBookmarkList());
        return true;
    }

    public User appendAccount(String login, String password) {
        User newUser = createUser(login, password);
        if (userMap.containsKey(login)) {
            return null;
        }
        userMap.put(login, currentUser);
        return newUser;
    }

    public boolean adminAppendAccount(String login, String password) {
        if (!currentUser.isAdminRights()) {
            return false;
        }
        return appendAccount(login, password) != null;
    }

    public boolean deleteUser(String login) {
        return userMap.remove(login) != null;
    }

    public void serializeAccounts() {
        dbService.saveAccountsData(userMap);
    }

    public void deserializeAccounts() {
        setUserMap(dbService.takeAccountsData());
    }

    public BookmarkService getCurrentBookmarks() {
        return currentBookmarks;
    }

    public void setCurrentBookmarks(BookmarkService currentBookmarks) {
        this.currentBookmarks = currentBookmarks;
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
