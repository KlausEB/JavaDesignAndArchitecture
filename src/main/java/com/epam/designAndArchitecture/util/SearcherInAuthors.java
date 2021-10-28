package com.epam.designAndArchitecture.util;

import com.epam.designAndArchitecture.entities.Author;
import com.epam.designAndArchitecture.entities.Book;

import java.util.ArrayList;
import java.util.List;

public class SearcherInAuthors {
    private final List<Author> authors;

    public SearcherInAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public boolean searchAuthorAndAppendBook(String authorName,
                                             String bookName, int yearOfPublishing, int numberOfPages, String bookISBN) {
        for (Author currentAuthor : authors) {
            String currentAuthorName = currentAuthor.getAuthorName();
            if (currentAuthorName.equals(authorName)) {
                return currentAuthor.appendBook(bookName, yearOfPublishing, numberOfPages, bookISBN);
            }
        }
        return false;
    }

    public boolean searchBookAndDelete(String bookISBN) {
        for (Author currentAuthor : authors) {
            if (currentAuthor.deleteBook(bookISBN)) {
                return true;
            }
        }
        return false;
    }

    public List<Book> searchBooksByPartAuthorName(String partName) {
        List<Book> booksByPartAuthorName = new ArrayList<>();
        for (Author currentAuthor : authors) {
            String currentAuthorName = currentAuthor.getAuthorName();
            if (currentAuthorName.contains(partName)) {
                List<Book> currentBooks = currentAuthor.takeBooks();
                booksByPartAuthorName.addAll(currentBooks);
            }
        }
        return booksByPartAuthorName;
    }

    public List<Book> searchBooksByPartName(String partName) {
        List<Book> booksByPartName = new ArrayList<>();
        for (Author currentAuthor : authors) {
            List<Book> currentBooks = currentAuthor.takeBooks(partName);
            booksByPartName.addAll(currentBooks);
        }
        return booksByPartName;
    }

    public Book searchBookByISBN(String isbn) {
        for (Author currentAuthor : authors) {
            Book bookByISBN = currentAuthor.takeBookByISBN(isbn);
            if (bookByISBN != null) {
                return bookByISBN;
            }
        }
        return null;
    }

    public List<Book> searchBooksByYearRange(int minYear, int maxYear) {
        List<Book> booksByYearRange = new ArrayList<>();
        for (Author currentAuthor : authors) {
            List<Book> currentBooks = currentAuthor.takeBooks(minYear, maxYear);
            booksByYearRange.addAll(currentBooks);
        }
        return booksByYearRange;
    }

    public List<Book> searchBookByYearPagesPartName(int yearOfPublishing, int numberOfPages, String partName) {
        List<Book> booksByYearPagesPartName = new ArrayList<>();
        for (Author currentAuthor : authors) {
            List<Book> currentBooks = currentAuthor.takeBooks(yearOfPublishing, numberOfPages, partName);
            booksByYearPagesPartName.addAll(currentBooks);
        }
        return booksByYearPagesPartName;
    }
}
