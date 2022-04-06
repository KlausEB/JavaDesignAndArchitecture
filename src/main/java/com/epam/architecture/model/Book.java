package com.epam.architecture.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "BOOK", schema = "HOME_LIBRARY")
public class Book implements Serializable {
    @Id
    @Column(name = "ISBN")
    private String bookIsbn;
    @ManyToOne
    @JoinColumn(name = "AUTHORNAME")
    private Author authorName;
    private String bookName;
    private int yearOfPublishing;
    private int numberOfPages;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ISBN")
    private Set<Bookmark> bookmarks;

    public Book(Author authorName, String bookName, int yearOfPublishing, int numberOfPages, String bookIsbn) {
        this.authorName = authorName;
        this.bookName = bookName;
        this.yearOfPublishing = yearOfPublishing;
        this.numberOfPages = numberOfPages;
        this.bookIsbn = bookIsbn;
    }

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", yearOfPublishing=" + yearOfPublishing +
                ", numberOfPages=" + numberOfPages +
                ", bookISBN='" + bookIsbn + '\'' +
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
        return bookIsbn.equals(book.bookIsbn);
    }

    @Override
    public int hashCode() {
        int result = bookName.hashCode();
        result = 31 * result + yearOfPublishing;
        result = 31 * result + numberOfPages;
        result = 31 * result + bookIsbn.hashCode();
        return result;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public Author getAuthorName() {
        return authorName;
    }

    public void setAuthorName(Author authorName) {
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

    public Set<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(Set<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }
}
