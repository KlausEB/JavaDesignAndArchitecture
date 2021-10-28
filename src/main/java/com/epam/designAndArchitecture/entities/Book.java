package com.epam.designAndArchitecture.entities;

import com.epam.designAndArchitecture.IPotentialJSON;

public class Book implements IPotentialJSON {
    private String bookName;
    private int yearOfPublishing;
    private int numberOfPages;
    private String bookISBN;

    public Book(String bookName, int yearOfPublishing, int numberOfPages, String bookISBN) {
        this.bookName = bookName;
        this.yearOfPublishing = yearOfPublishing;
        this.numberOfPages = numberOfPages;
        this.bookISBN = bookISBN;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", yearOfPublishing=" + yearOfPublishing +
                ", numberOfPages=" + numberOfPages +
                ", bookISBN='" + bookISBN + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (yearOfPublishing != book.yearOfPublishing) return false;
        if (numberOfPages != book.numberOfPages) return false;
        if (!bookName.equals(book.bookName)) return false;
        return bookISBN.equals(book.bookISBN);
    }

    @Override
    public int hashCode() {
        int result = bookName.hashCode();
        result = 31 * result + yearOfPublishing;
        result = 31 * result + numberOfPages;
        result = 31 * result + bookISBN.hashCode();
        return result;
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

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }
}
