package com.epam.designAndArchitecture.util;

import com.epam.designAndArchitecture.entities.Author;

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

    public List<String> searchBooksByPartAuthorName(String partName) {
        List<String> booksByPartAuthorName = new ArrayList<>();
        for (Author currentAuthor : authors) {
            String currentAuthorName = currentAuthor.getAuthorName();
            if (currentAuthorName.contains(partName)) {
                List<String> currentBooks = currentAuthor.takeStringBookList();
                booksByPartAuthorName.addAll(currentBooks);
            }
        }
        return booksByPartAuthorName;
    }

    public List<String> searchBooksByPartName(String partName) {
        List<String> booksByPartName = new ArrayList<>();
        for (Author currentAuthor : authors) {
            List<String> currentBooks = currentAuthor.takeStringBookList(partName);
            booksByPartName.addAll(currentBooks);
        }
        return booksByPartName;
    }

    public String searchBookByISBN(String isbn) {
        for (Author currentAuthor : authors) {
            String bookByISBN = currentAuthor.takeBookByISBN(isbn);
            if (bookByISBN != null) {
                return bookByISBN;
            }
        }
        return "Not found";
    }

    public List<String> searchBooksByYearRange(int minYear, int maxYear) {
        List<String> booksByYearRange = new ArrayList<>();
        for (Author currentAuthor : authors){
            List<String> currentBooks = currentAuthor.takeStringBookList(minYear, maxYear);
            booksByYearRange.addAll(currentBooks);
        }
        return booksByYearRange;
    }

    public List<String> searchBookByYearPagesPartName(int yearOfPublishing, int numberOfPages, String partName) {
        List<String> booksByYearPagesPartName = new ArrayList<>();
        for (Author currentAuthor : authors){
            List<String> currentBooks = currentAuthor.takeStringBookList(yearOfPublishing, numberOfPages, partName);
            booksByYearPagesPartName.addAll(currentBooks);
        }
        return booksByYearPagesPartName;
    }
}
