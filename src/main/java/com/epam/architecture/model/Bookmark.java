package com.epam.architecture.model;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BOOKMARK", schema = "HOME_LIBRARY")
@EqualsAndHashCode
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
