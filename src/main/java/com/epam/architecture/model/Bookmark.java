package com.epam.architecture.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BOOKMARK", schema = "HOME_LIBRARY")
public class Bookmark implements Serializable {
    @Id
    @GeneratedValue(generator = "auto_inc")
    private long bookmarkId;
    @ManyToOne
    @JoinColumn(name = "USERLOGIN")
    private User userLogin;
    @ManyToOne
    @JoinColumn(name = "ISBN")
    private Book book;
    private int pageNumber;

    public Bookmark(User userLogin, Book book, int pageNumber) {
        this.userLogin = userLogin;
        this.book = book;
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
        return book.equals(bookmark.book);
    }

    @Override
    public int hashCode() {
        int result = book.hashCode();
        result = 31 * result + pageNumber;
        return result;
    }

    public long getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(long bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public User getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(User userLogin) {
        this.userLogin = userLogin;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book isbn) {
        this.book = isbn;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
