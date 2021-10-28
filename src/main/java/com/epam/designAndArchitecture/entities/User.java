package com.epam.designAndArchitecture.entities;

import com.epam.designAndArchitecture.IPotentialJSON;

import java.util.ArrayList;
import java.util.List;

public class User implements IPotentialJSON {
    private final List<Bookmark> bookmarkList = new ArrayList<>();
    private String login;
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login) {
        this.login = login;
    }

    public void appendBookmark(Bookmark bookmark) {
        bookmarkList.add(bookmark);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return login.equals(user.login);
    }

    @Override
    public int hashCode() {
        return login.hashCode();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Bookmark> getBookmarkList() {
        return bookmarkList;
    }
}
