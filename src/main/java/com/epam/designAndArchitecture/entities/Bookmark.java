package com.epam.designAndArchitecture.entities;

public class Bookmark {
    private String ISBN;
    private int pageNumber;

    public Bookmark(String ISBN, int pageNumber) {
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
