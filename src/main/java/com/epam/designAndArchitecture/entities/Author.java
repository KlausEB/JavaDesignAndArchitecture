package com.epam.designAndArchitecture.entities;

import com.epam.designAndArchitecture.SerializableObject;
import com.epam.designAndArchitecture.util.BooksBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Author implements SerializableObject {
    private Map<String, Book> ISBNToBooks = new HashMap<>();
    private final BooksBuilder booksBuilder = new BooksBuilder(ISBNToBooks);
    private String authorName;

    public Author(String authorName) {
        this.authorName = authorName;
    }

    public Author() {
    }

    public Book createBook(String bookName, int yearOfPublishing, int numberOfPages, String bookISBN) {
        return new Book(bookName, yearOfPublishing, numberOfPages, bookISBN);
    }

    public boolean appendBook(String bookName, int yearOfPublishing, int numberOfPages, String bookISBN) {
        Book book = createBook(bookName, yearOfPublishing, numberOfPages, bookISBN);
        return ISBNToBooks.put(bookISBN, book) == null;
    }

    public boolean deleteBook(String bookISBN) {
        return ISBNToBooks.remove(bookISBN) != null;
    }

    public List<Book> takeBooks() {
        return booksBuilder.buildBookList();
    }

    public List<Book> takeBooks(String partName) {
        return booksBuilder.buildBookList(partName);
    }

    public Book takeBookByISBN(String isbn) {
        return booksBuilder.buildBookByISBN(isbn);
    }

    public List<Book> takeBooks(int minYear, int maxYear) {
        return booksBuilder.buildBookList(minYear, maxYear);
    }

    public List<Book> takeBooks(int yearOfPublishing, int numberOfPages, String partName) {
        return booksBuilder.buildBookList(yearOfPublishing, numberOfPages, partName);
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

    public void setISBNToBooks(Map<String, Book> ISBNToBooks) {
        this.ISBNToBooks = ISBNToBooks;
    }
}
