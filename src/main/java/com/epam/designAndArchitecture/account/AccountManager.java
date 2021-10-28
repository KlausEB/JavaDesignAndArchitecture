package com.epam.designAndArchitecture.account;


import com.epam.designAndArchitecture.entities.User;
import com.epam.designAndArchitecture.util.BookmarkService;

import java.util.HashMap;
import java.util.Map;

public class AccountManager {
    private final Map<String, User> userMap = new HashMap<>();
    private User currentUser;
    private BookmarkService currentBookmarks;

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

    public BookmarkService getCurrentBookmarks() {
        return currentBookmarks;
    }

    public void setCurrentBookmarks(BookmarkService currentBookmarks) {
        this.currentBookmarks = currentBookmarks;
    }

    public boolean deleteUser(String login) {
        return userMap.remove(login) != null;
    }
}
