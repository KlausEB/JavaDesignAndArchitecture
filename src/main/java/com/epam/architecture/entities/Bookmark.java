package com.epam.architecture.entities;

import java.io.Serializable;

public class Bookmark implements Serializable {
    private String userLogin;
    private String isbn;
    private int pageNumber;

    public Bookmark(String userLogin, String isbn, int pageNumber) {
        this.userLogin = userLogin;
        this.isbn = isbn;
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
        return isbn.equals(bookmark.isbn);
    }

    @Override
    public int hashCode() {
        int result = isbn.hashCode();
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
