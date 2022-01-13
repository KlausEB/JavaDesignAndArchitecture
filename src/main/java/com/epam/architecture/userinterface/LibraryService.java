package com.epam.architecture.userinterface;

import com.epam.architecture.App;
import com.epam.architecture.account.AccountManager;
import com.epam.architecture.entities.Book;
import com.epam.architecture.exceptions.HistoryException;
import com.epam.architecture.library.LiteratureManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LibraryService {
    public static final Logger logger = App.logger;
    public static final Properties properties = new Properties();
    public static File historyFile;

    static {
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream("src/main/resources/source.properties"))) {
            properties.load(reader);
            historyFile = new File(properties.getProperty("historyFile"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final AccountManager accountManager = new AccountManager();
    private final LiteratureManager literatureManager = new LiteratureManager();

    public static String convertCollectionToString(Collection<?> objects) {
        StringBuilder stringObjects = new StringBuilder();
        for (Object currentObject : objects) {
            stringObjects.append(currentObject.toString()).append('\n');
        }
        return stringObjects.toString();
    }

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
        if (literatureManager.deleteBook(bookISBN)) {
            accountManager.deleteAllBookBookmarks(bookISBN);
            return true;
        }
        return false;
    }

    public boolean appendAuthor(String authorName) {
        return literatureManager.appendAuthor(authorName);
    }

    public boolean deleteAuthor(String authorName) {
        return literatureManager.deleteAuthor(authorName);
    }

    public List<Book> booksByPartAuthorName(String partName) {
        List<Book> booksByPartAuthorName = literatureManager.searchBooksByPartAuthorName(partName);
        return booksByPartAuthorName;
    }

    public List<Book> booksByPartName(String partName) {
        List<Book> booksByPartName = literatureManager.searchBooksByPartName(partName);
        return booksByPartName;
    }

    public Book bookByISBN(String isbn) {
        return literatureManager.searchBookByISBN(isbn);
    }

    public List<Book> booksByYearRange(int minYear, int maxYear) {
        List<Book> booksByYearRange = literatureManager.searchBooksByYearRange(minYear, maxYear);
        return booksByYearRange;
    }

    public List<Book> booksByYearPagesPartName(int yearOfPublishing, int numberOfPages, String partName) {
        List<Book> booksByYearPagesPartName = literatureManager.searchBooksByYearPagesPartName(yearOfPublishing, numberOfPages, partName);
        return booksByYearPagesPartName;
    }

    public boolean appendBookmark(String login, String isbn, int pageNumber) {
        return accountManager.appendBookmark(login, isbn, pageNumber);
    }

    public boolean deleteBookmark(String login, String isbn, int pageNumber) {
        return accountManager.deleteBookmark(login, isbn, pageNumber);
    }

    public List<Book> booksWithUserBookmarks(String login) {
        Set<String> booksISBNWithUserBookmarks = accountManager.takeBooksWithUserBookmarks(login);
        List<Book> booksWithUserBookmarks = new ArrayList<>();
        for (String currentISBN : booksISBNWithUserBookmarks) {
            booksWithUserBookmarks.add(literatureManager.searchBookByISBN(currentISBN));
        }
        return booksWithUserBookmarks;
    }

    public boolean appendNewUser(String adminLogin, String newLogin, String password) {
        return accountManager.appendAdminAccount(adminLogin, newLogin, password);
    }

    public boolean banUser(String adminLogin, String deleteLogin) {
        return accountManager.deleteUser(adminLogin, deleteLogin);
    }

    public String takeHistory(String login) {
        if (accountManager.userIsAdmin(login)) {
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
}
