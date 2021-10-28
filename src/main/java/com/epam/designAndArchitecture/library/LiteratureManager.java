package com.epam.designAndArchitecture.library;

import com.epam.designAndArchitecture.entities.Author;
import com.epam.designAndArchitecture.util.SearcherInAuthors;

import java.util.ArrayList;
import java.util.List;

public class LiteratureManager {
    private final List<Author> authors = new ArrayList<>();
    private final SearcherInAuthors searcherInAuthors = new SearcherInAuthors(authors);

    public Author createAuthor(String authorName) {
        return new Author(authorName);
    }

    public boolean appendAuthor(String authorName) {
        Author newAuthor = createAuthor(authorName);
        return authors.add(newAuthor);
    }

    public boolean deleteAuthor(String authorName) {
        Author deletingAuthor = createAuthor(authorName);
        return authors.remove(deletingAuthor);
    }

    public boolean appendBook(String authorName,
                              String bookName, int yearOfPublishing, int numberOfPages, String bookISBN) {
        return searcherInAuthors.searchAuthorAndAppendBook(authorName,
                bookName, yearOfPublishing, numberOfPages, bookISBN);
    }

    public boolean deleteBook(String bookISBN) {
        return searcherInAuthors.searchBookAndDelete(bookISBN);
    }

    public List<String> searchBooksByPartAuthorName(String partName) {
        return searcherInAuthors.searchBooksByPartAuthorName(partName);
    }

    public List<String> searchBooksByPartName(String partName) {
        return searcherInAuthors.searchBooksByPartName(partName);
    }

    public String searchBookByISBN(String isbn) {
        return searcherInAuthors.searchBookByISBN(isbn);
    }

    public List<String> searchBooksByYearRange(int minYear, int maxYear) {
        return searcherInAuthors.searchBooksByYearRange(minYear, maxYear);
    }

    public List<String> searchBooksByYearPagesPartName(int yearOfPublishing, int numberOfPages, String partName) {
        return searcherInAuthors.searchBookByYearPagesPartName(yearOfPublishing, numberOfPages, partName);
    }
}
