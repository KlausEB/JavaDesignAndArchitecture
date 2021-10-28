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
