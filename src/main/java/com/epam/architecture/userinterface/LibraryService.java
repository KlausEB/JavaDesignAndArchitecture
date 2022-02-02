package com.epam.architecture.userinterface;

import com.epam.architecture.account.AccountManager;
import com.epam.architecture.entities.Book;
import com.epam.architecture.exceptions.HistoryException;
import com.epam.architecture.library.LiteratureManager;
import com.epam.architecture.roles.RoleEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LibraryService {
    public static final Logger logger = LogManager.getLogger();
    public static final Properties properties = new Properties();
    public static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    public static File historyFile;

    private static volatile LibraryService libraryService;

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

    public static LibraryService getInstanceWithDeserializeData() {
        if (libraryService == null) {
            synchronized (LibraryService.class) {
                if (libraryService == null) {
                    startWorking();
                }
            }
        }
        return libraryService;
    }

    private static void startWorking() {
        libraryService = new LibraryService();
        libraryService.requestDeserializeData();
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

    public boolean addBookmark(String login, String isbn, int pageNumber) {
        return accountManager.addBookmark(login, isbn, pageNumber);
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

    public boolean addNewUser(String newLogin, String password) {
        return accountManager.addAdminAccount(newLogin, password);
    }

    public boolean banUser(String login) {
        return accountManager.deleteUser(login);
    }

    public String takeHistory() {
        try {
            return Files.readString(Paths.get(historyFile.getPath()));
        } catch (IOException e) {
            HistoryException exception = new HistoryException(e);
            logger.catching(exception);
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

    public RoleEnum userRole(String login) {
        return accountManager.getUserRole(login);
    }

    public void closeSourceService() {
        sessionFactory.close();
    }
}
