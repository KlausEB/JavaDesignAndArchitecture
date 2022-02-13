package com.epam.architecture.endpoint.dto;

import java.io.Serializable;

public class BookmarkDTO implements Serializable {
    private String isbn;
    private int pageNumber;

    public BookmarkDTO(String isbn, int pageNumber) {
        this.isbn = isbn;
        this.pageNumber = pageNumber;
    }

    public BookmarkDTO() {
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
        return isbn.equals(that.isbn);
    }

    @Override
    public int hashCode() {
        int result = isbn.hashCode();
        result = 31 * result + pageNumber;
        return result;
    }
}
