package com.epam.architecture.library;

import com.epam.architecture.App;
import com.epam.architecture.datasource.EntityTypes;
import com.epam.architecture.datasource.HTwoDataSourceService;
import com.epam.architecture.datasource.LibraryDAO;
import com.epam.architecture.entities.Author;
import com.epam.architecture.entities.Book;
import com.epam.architecture.userinterface.LibraryService;

import java.util.*;
import java.util.stream.Collectors;

public class LiteratureManager {
    public static final String PATH_TO_AUTHOR_JSON_FILE = LibraryService.properties.getProperty("authorDataSource");
    public static final String PATH_TO_BOOK_JSON_FILE = LibraryService.properties.getProperty("bookDataSource");
    private final LibraryDAO<Author> authorDataSourceService = new HTwoDataSourceService<>(EntityTypes.AUTHOR);
    private final LibraryDAO<Book> bookDataSourceService = new HTwoDataSourceService<>(EntityTypes.BOOK);
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
        Author deletedAuthor = createAuthor(authorName);
        if (authorsSet.remove(deletedAuthor)) {
            authorDataSourceService.deleteData(deletedAuthor);
            List<Book> deletedBooks = new ArrayList<>();
            if (isbnToBookMap.values().removeIf(x -> {
                if (x.getAuthorName().equals(authorName)) {
                    deletedBooks.add(x);
                    return true;
                }
                return false;
            })) {
                bookDataSourceService.deleteData(deletedBooks.toArray(Book[]::new));
            }
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
        bookDataSourceService.saveData(currentNewBook);
        return true;
    }

    public boolean deleteBook(String bookISBN) {
        Book deletedBook = isbnToBookMap.remove(bookISBN);
        if (deletedBook != null) {
            bookDataSourceService.deleteData(deletedBook);
            return true;
        }
        return false;
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
        List<Author> authors = authorDataSourceService.restoreData();
        List<Book> books = bookDataSourceService.restoreData();
        authorsSet.addAll(authors);
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
