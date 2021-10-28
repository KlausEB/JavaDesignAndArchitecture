package com.epam.designAndArchitecture.util;

import com.epam.designAndArchitecture.entities.Book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class BooksBuilder {
    private final Map<String, Book> ISBNToBooks;

    public BooksBuilder(Map<String, Book> ISBNToBooks) {
        this.ISBNToBooks = ISBNToBooks;
    }

    public List<Book> buildBookList() {
        Collection<Book> bookValues = ISBNToBooks.values();
        return new ArrayList<>(bookValues);
    }

    public List<Book> buildBookList(String partBookName) {
        List<Book> bookList = new ArrayList<>();
        Collection<Book> bookValues = ISBNToBooks.values();
        for (Book currentBook : bookValues) {
            String currentBookName = currentBook.getBookName();
            if (currentBookName.contains(partBookName)) {
                bookList.add(currentBook);
            }
        }
        return bookList;
    }

    public Book buildBookByISBN(String isbn) {
        Book book = ISBNToBooks.get(isbn);
        return book;
    }

    public List<Book> buildBookList(int minYear, int maxYear) {
        List<Book> bookList = new ArrayList<>();
        Collection<Book> bookValues = ISBNToBooks.values();
        for (Book currentBook : bookValues) {
            int currentBookYearOfPublishing = currentBook.getYearOfPublishing();
            if (minYear <= currentBookYearOfPublishing && currentBookYearOfPublishing <= maxYear) {
                bookList.add(currentBook);
            }
        }
        return bookList;
    }

    public List<Book> buildBookList(int yearOfPublishing, int numberOfPages, String partName) {
        List<Book> bookList = new ArrayList<>();
        Collection<Book> bookValues = ISBNToBooks.values();
        for (Book currentBook : bookValues) {
            int currentBookYearOfPublishing = currentBook.getYearOfPublishing();
            int currentBookNumberOfPages = currentBook.getNumberOfPages();
            String currentBookName = currentBook.getBookName();
            if (currentBookYearOfPublishing == yearOfPublishing
                    && currentBookNumberOfPages == numberOfPages
                    && currentBookName.contains(partName)) {
                bookList.add(currentBook);
            }
        }
        return bookList;
    }
}
