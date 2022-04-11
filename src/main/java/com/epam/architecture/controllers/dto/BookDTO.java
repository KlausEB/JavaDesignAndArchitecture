package com.epam.architecture.controllers.dto;

import com.epam.architecture.model.Author;

import java.io.Serializable;

public class BookDTO implements Serializable {
    private String bookISBN;
    private String authorName;
    private String bookName;
    private int yearOfPublishing;
    private int numberOfPages;

    public BookDTO(String bookISBN, String authorName, String bookName, int yearOfPublishing, int numberOfPages) {
        this.bookISBN = bookISBN;
        this.authorName = authorName;
        this.bookName = bookName;
        this.yearOfPublishing = yearOfPublishing;
        this.numberOfPages = numberOfPages;
    }

    public BookDTO() {
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(Author author) {
        this.authorName = author.getAuthorName();
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookDTO bookDTO = (BookDTO) o;

        if (yearOfPublishing != bookDTO.yearOfPublishing) return false;
        if (numberOfPages != bookDTO.numberOfPages) return false;
        if (!bookISBN.equals(bookDTO.bookISBN)) return false;
        if (!authorName.equals(bookDTO.authorName)) return false;
        return bookName.equals(bookDTO.bookName);
    }

    @Override
    public int hashCode() {
        int result = bookISBN.hashCode();
        result = 31 * result + authorName.hashCode();
        result = 31 * result + bookName.hashCode();
        result = 31 * result + yearOfPublishing;
        result = 31 * result + numberOfPages;
        return result;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "bookISBN='" + bookISBN + '\'' +
                ", authorName='" + authorName + '\'' +
                ", bookName='" + bookName + '\'' +
                ", yearOfPublishing=" + yearOfPublishing +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}
