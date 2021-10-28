package com.epam.designAndArchitecture.entities;

import com.epam.designAndArchitecture.IPotentialJSON;
import com.epam.designAndArchitecture.util.BooksBuilder;

import java.util.*;

public class Author implements IPotentialJSON {
    private final Map<String, Book> ISBNToBooks = new HashMap<>();
    private final BooksBuilder booksBuilder = new BooksBuilder(ISBNToBooks);
    private String authorName;

    public Author(String authorName) {
        this.authorName = authorName;
    }

    public Book createBook(String bookName, int yearOfPublishing, int numberOfPages, String bookISBN) {
        return new Book(bookName, yearOfPublishing, numberOfPages, bookISBN);
    }

    public boolean appendBook(String bookName, int yearOfPublishing, int numberOfPages, String bookISBN) {
        Book book = createBook(bookName, yearOfPublishing, numberOfPages, bookISBN);
        return ISBNToBooks.put(bookISBN, book) != null;
    }

    public boolean deleteBook(String bookISBN) {
        return ISBNToBooks.remove(bookISBN) != null;
    }

    public List<String> takeStringBookList() {
        return booksBuilder.buildStringBookList();
    }

    public List<String> takeStringBookList(String partName) {
        return booksBuilder.buildStringBookList(partName);
    }

    public String takeBookByISBN(String isbn) {
        return booksBuilder.buildBookByISBN(isbn);
    }

    public List<String> takeStringBookList(int minYear, int maxYear) {
        return booksBuilder.buildStringBookList(minYear, maxYear);
    }

    public List<String> takeStringBookList(int yearOfPublishing, int numberOfPages, String partName) {
        return booksBuilder.buildStringBookList(yearOfPublishing, numberOfPages, partName);
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Map<String, Book> getISBNToBooks() {
        return ISBNToBooks;
    }
}
