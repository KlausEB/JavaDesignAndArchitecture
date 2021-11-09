package com.epam.designAndArchitecture.library;

import com.epam.designAndArchitecture.App;
import com.epam.designAndArchitecture.dataSourceServices.DataSourceService;
import com.epam.designAndArchitecture.dataSourceServices.DataSourceType;
import com.epam.designAndArchitecture.entities.Author;
import com.epam.designAndArchitecture.entities.Book;

import java.util.*;
import java.util.stream.Collectors;

public class LiteratureManager {
    public static final String pathToAuthorJSONFile = App.properties.getProperty("authorDataSource");
    public static final String pathToBookJSONFile = App.properties.getProperty("bookDataSource");
    private final DataSourceService authorDataSourceService = new DataSourceService(pathToAuthorJSONFile, DataSourceType.AUTHOR);
    private final DataSourceService bookDataSourceService = new DataSourceService(pathToBookJSONFile, DataSourceType.BOOK);
    private final Map<String, Book> ISBNToBookMap = new HashMap<>();
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
            ISBNToBookMap.values().removeIf(x -> x.getAuthorName().equals(authorName));
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
        ISBNToBookMap.put(bookISBN, currentNewBook);
        return true;
    }

    public boolean deleteBook(String bookISBN) {
        return ISBNToBookMap.remove(bookISBN) != null;
    }

    public List<Book> searchBooksByPartAuthorName(String partName) {
        return ISBNToBookMap.values().stream()
                .filter(x -> x.getAuthorName().contains(partName))
                .collect(Collectors.toList());
    }

    public List<Book> searchBooksByPartName(String partName) {
        return ISBNToBookMap.values().stream().filter(x -> x.getBookName()
                .contains(partName))
                .collect(Collectors.toList());
    }

    public Book searchBookByISBN(String isbn) {
        return ISBNToBookMap.get(isbn);
    }

    public List<Book> searchBooksByYearRange(int minYear, int maxYear) {
        return ISBNToBookMap.values().stream()
                .filter(x -> (x.getYearOfPublishing() >= minYear
                        && x.getYearOfPublishing() <= maxYear))
                .collect(Collectors.toList());
    }

    public List<Book> searchBooksByYearPagesPartName(int yearOfPublishing, int numberOfPages, String partName) {
        return ISBNToBookMap.values().stream()
                .filter(x -> (x.getYearOfPublishing() == yearOfPublishing
                        && x.getNumberOfPages() == numberOfPages
                        && x.getBookName().contains(partName)))
                .collect(Collectors.toList());
    }

    public void saveLiteratureData() {
        Author[] authors = authorsSet.toArray(new Author[0]);
        authorDataSourceService.saveData(authors);
        Book[] books = ISBNToBookMap.values().toArray(new Book[0]);
        bookDataSourceService.saveData(books);
    }

    public void loadLiteratureData() {
        Author[] authors = (Author[]) authorDataSourceService.restoreData();
        Book[] books = (Book[]) bookDataSourceService.restoreData();
        authorsSet.addAll(Arrays.asList(authors));
        for (Book book : books) {
            ISBNToBookMap.put(book.getBookISBN(), book);
        }
    }

    public Set<Author> getAuthorsSet() {
        return authorsSet;
    }

    public void setAuthorsSet(Set<Author> authorsSet) {
        this.authorsSet = authorsSet;
    }
}
