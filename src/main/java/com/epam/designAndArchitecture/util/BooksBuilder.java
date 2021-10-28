package com.epam.designAndArchitecture.util;

import com.epam.designAndArchitecture.entities.Book;

import java.util.*;

public class BooksBuilder {
    private final Map<String, Book> ISBNToBooks;

    public BooksBuilder(Map<String, Book> ISBNToBooks) {
        this.ISBNToBooks = ISBNToBooks;
    }

    public List<String> buildStringBookList() {
        List<String> bookList = new ArrayList<>();
        Collection<Book> bookValues = ISBNToBooks.values();
        for (Book currentBook : bookValues) {
            bookList.add(currentBook.toString());
        }
        return bookList;
    }

    public List<String> buildStringBookList(String partBookName) {
        List<String> bookList = new ArrayList<>();
        Collection<Book> bookValues = ISBNToBooks.values();
        for (Book currentBook : bookValues) {
            String currentBookName = currentBook.getBookName();
            if (currentBookName.contains(partBookName)) {
                bookList.add(currentBook.toString());
            }
        }
        return bookList;
    }

    public String buildBookByISBN(String isbn) {
        Book book = ISBNToBooks.get(isbn);
        if (book == null) {
            return null;
        }
        return book.toString();
    }

    public List<String> buildStringBookList(int minYear, int maxYear) {
        List<String> bookList = new ArrayList<>();
        Collection<Book> bookValues = ISBNToBooks.values();
        for (Book currentBook : bookValues) {
            int currentBookYearOfPublishing = currentBook.getYearOfPublishing();
            if (minYear <= currentBookYearOfPublishing && currentBookYearOfPublishing <= maxYear){
                bookList.add(currentBook.toString());
            }
        }
        return bookList;
    }

    public List<String> buildStringBookList(int yearOfPublishing, int numberOfPages, String partName) {
        List<String> bookList = new ArrayList<>();
        Collection<Book> bookValues = ISBNToBooks.values();
        for (Book currentBook : bookValues) {
            int currentBookYearOfPublishing = currentBook.getYearOfPublishing();
            int currentBookNumberOfPages = currentBook.getNumberOfPages();
            String currentBookName = currentBook.getBookName();
            if (currentBookYearOfPublishing == yearOfPublishing
                    && currentBookNumberOfPages == numberOfPages
                    && currentBookName.contains(partName)){
                bookList.add(currentBook.toString());
            }
        }
        return bookList;
    }
}
