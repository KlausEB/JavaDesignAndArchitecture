package com.epam.designAndArchitecture.entities;

public class Bookmark {
    private Book book;
    private int pageNumber;

    public Bookmark(Book book, int pageNumber) {
        this.book = book;
        this.pageNumber = pageNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bookmark bookmark = (Bookmark) o;

        if (pageNumber != bookmark.pageNumber) return false;
        return book != null ? book.equals(bookmark.book) : bookmark.book == null;
    }

    @Override
    public int hashCode() {
        int result = book != null ? book.hashCode() : 0;
        result = 31 * result + pageNumber;
        return result;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
