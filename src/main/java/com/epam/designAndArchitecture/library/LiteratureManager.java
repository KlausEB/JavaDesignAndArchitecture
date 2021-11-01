package com.epam.designAndArchitecture.library;

import com.epam.designAndArchitecture.entities.Author;
import com.epam.designAndArchitecture.entities.Book;
import com.epam.designAndArchitecture.util.SearcherInAuthors;

import java.util.ArrayList;
import java.util.List;

public class LiteratureManager {
    private static final String pathToJSONFile = "DB/literature.json";
    private final LiteratureDBService dbService = new LiteratureDBService(pathToJSONFile);
    private List<Author> authors = new ArrayList<>();
    private SearcherInAuthors searcherInAuthors = new SearcherInAuthors(authors);

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

    public List<Book> searchBooksByPartAuthorName(String partName) {
        return searcherInAuthors.searchBooksByPartAuthorName(partName);
    }

    public List<Book> searchBooksByPartName(String partName) {
        return searcherInAuthors.searchBooksByPartName(partName);
    }

    public Book searchBookByISBN(String isbn) {
        return searcherInAuthors.searchBookByISBN(isbn);
    }

    public List<Book> searchBooksByYearRange(int minYear, int maxYear) {
        return searcherInAuthors.searchBooksByYearRange(minYear, maxYear);
    }

    public List<Book> searchBooksByYearPagesPartName(int yearOfPublishing, int numberOfPages, String partName) {
        return searcherInAuthors.searchBookByYearPagesPartName(yearOfPublishing, numberOfPages, partName);
    }

    public void serializeLiteratureData() {
        dbService.saveLiteratureData(authors);
    }

    public void deserializeLiteratureData() {
        setAuthors(dbService.takeLiteratureData());
        searcherInAuthors.setAuthors(authors);
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public SearcherInAuthors getSearcherInAuthors() {
        return searcherInAuthors;
    }

    public void setSearcherInAuthors(SearcherInAuthors searcherInAuthors) {
        this.searcherInAuthors = searcherInAuthors;
    }
}
