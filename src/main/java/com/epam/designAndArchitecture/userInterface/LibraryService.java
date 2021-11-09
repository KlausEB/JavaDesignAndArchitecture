package com.epam.designAndArchitecture.userInterface;

import com.epam.designAndArchitecture.App;
import com.epam.designAndArchitecture.account.AccountManager;
import com.epam.designAndArchitecture.entities.Book;
import com.epam.designAndArchitecture.exceptions.HistoryException;
import com.epam.designAndArchitecture.library.LiteratureManager;
import com.epam.designAndArchitecture.util.BookmarkService;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class LibraryService {
    public static final Logger logger = App.logger;
    public static File historyFile = new File(App.properties.getProperty("historyFile"));
    private final AccountManager accountManager = new AccountManager();
    private final LiteratureManager literatureManager = new LiteratureManager();

    public boolean signUpAccount(String login, String password) {
        return accountManager.signUpUser(login, password);
    }

    public boolean logInAccount(String login, String password) {
        return accountManager.logInUser(login, password);
    }

    public boolean appendBook(String authorName,
                              String bookName, int yearOfPublishing, int numberOfPages, String bookISBN) {
        return literatureManager.appendBook(authorName,
                bookName, yearOfPublishing, numberOfPages, bookISBN);
    }

    public boolean deleteBook(String bookISBN) {
        return literatureManager.deleteBook(bookISBN);
    }

    public boolean appendAuthor(String authorName) {
        return literatureManager.appendAuthor(authorName);
    }

    public boolean deleteAuthor(String authorName) {
        return literatureManager.deleteAuthor(authorName);
    }

    public String booksByPartAuthorName(String partName) {
        List<Book> booksByPartAuthorName = literatureManager.searchBooksByPartAuthorName(partName);
        return convertCollectionToString(booksByPartAuthorName);
    }

    public String booksByPartName(String partName) {
        List<Book> booksByPartName = literatureManager.searchBooksByPartName(partName);
        return convertCollectionToString(booksByPartName);
    }

    public String bookByISBN(String isbn) {
        return literatureManager.searchBookByISBN(isbn).toString();
    }

    public String booksByYearRange(int minYear, int maxYear) {
        List<Book> booksByYearRange = literatureManager.searchBooksByYearRange(minYear, maxYear);
        return convertCollectionToString(booksByYearRange);
    }

    public String booksByYearPagesPartName(int yearOfPublishing, int numberOfPages, String partName) {
        List<Book> booksByYearPagesPartName = literatureManager.searchBooksByYearPagesPartName(yearOfPublishing, numberOfPages, partName);
        return convertCollectionToString(booksByYearPagesPartName);
    }

    public boolean appendBookmark(String isbn, int pageNumber) {
        return accountManager.appendBookmark(isbn, pageNumber);
    }

    public boolean deleteBookmark(String isbn, int pageNumber) {
        return accountManager.deleteBookmark(isbn, pageNumber);
    }

    public String booksWithUserBookmarks() {
        BookmarkService bookmarkService = accountManager.getBookmarkService();
        Set<String> booksISBNWithUserBookmarks = bookmarkService.takeBooksWithBookmarks();
        StringBuilder booksWithUserBookmarks = new StringBuilder();
        for (String currentISBN : booksISBNWithUserBookmarks) {
            booksWithUserBookmarks.append(literatureManager.searchBookByISBN(currentISBN)).append('\n');
        }
        return booksWithUserBookmarks.toString();
    }

    public boolean appendNewUser(String login, String password) {
        return accountManager.appendAdminAccount(login, password);
    }

    public boolean banUser(String login) {
        return accountManager.deleteUser(login);
    }

    public String takeHistory() {
        if (accountManager.isAdmin()) {
            try {
                return Files.readString(Paths.get(historyFile.getPath()));
            } catch (IOException e) {
                HistoryException exception = new HistoryException(e);
                logger.catching(exception);
            }
        }
        return "You cannot read history";
    }

    public void requestSerializeData() {
        accountManager.saveAccountData();
        literatureManager.saveLiteratureData();
    }

    public void requestDeserializeData() {
        accountManager.loadAccountData();
        literatureManager.loadLiteratureData();
    }

    public String convertCollectionToString(Collection<?> objects) {
        StringBuilder stringObjects = new StringBuilder();
        for (Object currentObject : objects) {
            stringObjects.append(currentObject.toString()).append('\n');
        }
        return stringObjects.toString();
    }
}
