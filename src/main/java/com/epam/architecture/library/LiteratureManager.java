package com.epam.architecture.library;

import com.epam.architecture.App;
import com.epam.architecture.datasource.DataSourceService;
import com.epam.architecture.datasource.DataSourceType;
import com.epam.architecture.entities.Author;
import com.epam.architecture.entities.Book;

import java.util.*;
import java.util.stream.Collectors;

public class LiteratureManager {
    public static final String PATH_TO_AUTHOR_JSON_FILE = App.properties.getProperty("authorDataSource");
    public static final String PATH_TO_BOOK_JSON_FILE = App.properties.getProperty("bookDataSource");
    private final DataSourceService<Author> authorDataSourceService = new DataSourceService<>(PATH_TO_AUTHOR_JSON_FILE, DataSourceType.AUTHOR);
    private final DataSourceService<Book> bookDataSourceService = new DataSourceService<>(PATH_TO_BOOK_JSON_FILE, DataSourceType.BOOK);
    private final Map<String, Book> isbnToBookMap = new HashMap<>();
    private Set<Author> authorsSet = new HashSet<>();

    private Author createAuthor(String authorName) {
        return new Author(authorName);
    }

    public boolean appendAuthor(String authorName) {
        Author newAuthor = createAuthor(authorName);
        return authorsSet.add(newAuthor);
    }

    public boolean deleteAuthor(String authorName) {
        if (authorsSet.removeIf(x -> x.getAuthorName().equals(authorName))) {
            isbnToBookMap.values().removeIf(x -> x.getAuthorName().equals(authorName));
            return true;
        }
        return false;
    }

    private Book createBook(String authorName,
                            String bookName, int yearOfPublishing, int numberOfPages, String bookISBN) {
        return new Book(authorName, bookName, yearOfPublishing, numberOfPages, bookISBN);
    }

    public boolean appendBook(String authorName,
                              String bookName, int yearOfPublishing, int numberOfPages, String bookISBN) {
        Author currentBookAuthor = createAuthor(authorName);
        if (!authorsSet.contains(currentBookAuthor)) {
            return false;
        }
        Book currentNewBook = createBook(authorName, bookName, yearOfPublishing, numberOfPages, bookISBN);
        isbnToBookMap.put(bookISBN, currentNewBook);
        return true;
    }

    public boolean deleteBook(String bookISBN) {
        return isbnToBookMap.remove(bookISBN) != null;
    }

    public List<Book> searchBooksByPartAuthorName(String partName) {
        return isbnToBookMap.values().stream()
                .filter(x -> x.getAuthorName().contains(partName))
                .collect(Collectors.toList());
    }

    public List<Book> searchBooksByPartName(String partName) {
        return isbnToBookMap.values().stream().filter(x -> x.getBookName()
                .contains(partName))
                .collect(Collectors.toList());
    }

    public Book searchBookByISBN(String isbn) {
        return isbnToBookMap.get(isbn);
    }

    public List<Book> searchBooksByYearRange(int minYear, int maxYear) {
        return isbnToBookMap.values().stream()
                .filter(x -> (x.getYearOfPublishing() >= minYear
                        && x.getYearOfPublishing() <= maxYear))
                .collect(Collectors.toList());
    }

    public List<Book> searchBooksByYearPagesPartName(int yearOfPublishing, int numberOfPages, String partName) {
        return isbnToBookMap.values().stream()
                .filter(x -> (x.getYearOfPublishing() == yearOfPublishing
                        && x.getNumberOfPages() == numberOfPages
                        && x.getBookName().contains(partName)))
                .collect(Collectors.toList());
    }

    public void saveLiteratureData() {
        Author[] authors = authorsSet.toArray(new Author[0]);
        authorDataSourceService.saveData(authors);
        Book[] books = isbnToBookMap.values().toArray(new Book[0]);
        bookDataSourceService.saveData(books);
    }

    public void loadLiteratureData() {
        Author[] authors = authorDataSourceService.restoreData();
        Book[] books = bookDataSourceService.restoreData();
        authorsSet.addAll(Arrays.asList(authors));
        for (Book book : books) {
            isbnToBookMap.put(book.getBookISBN(), book);
        }
    }

    public Set<Author> getAuthorsSet() {
        return authorsSet;
    }

    public void setAuthorsSet(Set<Author> authorsSet) {
        this.authorsSet = authorsSet;
    }
}
