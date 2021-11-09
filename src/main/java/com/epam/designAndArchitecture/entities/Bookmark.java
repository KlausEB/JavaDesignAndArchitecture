package com.epam.designAndArchitecture.entities;

import com.epam.designAndArchitecture.SavableObject;

public class Bookmark implements SavableObject {
    private String userLogin;
    private String ISBN;
    private int pageNumber;

    public Bookmark(String userLogin, String ISBN, int pageNumber) {
        this.userLogin = userLogin;
        this.ISBN = ISBN;
        this.pageNumber = pageNumber;
    }

    public Bookmark() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bookmark bookmark = (Bookmark) o;

        if (pageNumber != bookmark.pageNumber) return false;
        return ISBN.equals(bookmark.ISBN);
    }

    @Override
    public int hashCode() {
        int result = ISBN.hashCode();
        result = 31 * result + pageNumber;
        return result;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
}
