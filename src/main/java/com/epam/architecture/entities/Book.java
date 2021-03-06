package com.epam.architecture.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "BOOK", schema = "HOME_LIBRARY")
public class Book implements Serializable {
    @Id
    @Column(name = "ISBN")
    private String bookISBN;
    private String authorName;
    private String bookName;
    private int yearOfPublishing;
    private int numberOfPages;

    public Book(String authorName, String bookName, int yearOfPublishing, int numberOfPages, String bookISBN) {
        this.authorName = authorName;
        this.bookName = bookName;
        this.yearOfPublishing = yearOfPublishing;
        this.numberOfPages = numberOfPages;
        this.bookISBN = bookISBN;
    }

    public Book() {
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

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public String getAuthorName() {
        return authorName;
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
}
