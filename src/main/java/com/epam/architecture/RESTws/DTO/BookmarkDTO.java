package com.epam.architecture.RESTws.DTO;

import java.io.Serializable;

public class BookmarkDTO implements Serializable {
    private String userLogin;
    private String isbn;
    private int pageNumber;

    public BookmarkDTO(String userLogin, String isbn, int pageNumber) {
        this.userLogin = userLogin;
        this.isbn = isbn;
        this.pageNumber = pageNumber;
    }

    public BookmarkDTO() {
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookmarkDTO that = (BookmarkDTO) o;

        if (pageNumber != that.pageNumber) return false;
        if (!userLogin.equals(that.userLogin)) return false;
        return isbn.equals(that.isbn);
    }

    @Override
    public int hashCode() {
        int result = userLogin.hashCode();
        result = 31 * result + isbn.hashCode();
        result = 31 * result + pageNumber;
        return result;
    }

    @Override
    public String toString() {
        return "BookmarkDTO{" +
                "userLogin='" + userLogin + '\'' +
                ", isbn='" + isbn + '\'' +
                ", pageNumber=" + pageNumber +
                '}';
    }
}
