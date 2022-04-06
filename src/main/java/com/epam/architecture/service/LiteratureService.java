package com.epam.architecture.service;

import com.epam.architecture.model.Author;
import com.epam.architecture.model.Book;

import java.util.List;

public interface LiteratureService {
    default Author createAuthor(String authorName) {
        return new Author(authorName);
    }

    void appendAuthor(String authorName);

    void deleteAuthor(String authorName);

    boolean appendBook(String authorName,
                       String bookName, int yearOfPublishing, int numberOfPages, String bookISBN);

    void deleteBook(String bookISBN);

    Book createBook(String authorName,
                    String bookName, int yearOfPublishing, int numberOfPages, String bookISBN);

    List<Book> searchBooksByPartAuthorName(String partName);

    List<Book> searchBooksByPartName(String partName);

    Book searchBookByISBN(String isbn);

    List<Book> searchBooksByYearRange(int minYear, int maxYear);

    List<Book> searchBooksByYearPagesPartName(int yearOfPublishing, int numberOfPages, String partName);
}
