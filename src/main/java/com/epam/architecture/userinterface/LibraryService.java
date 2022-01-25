package com.epam.architecture.userinterface;

import com.epam.architecture.App;
import com.epam.architecture.account.AccountManager;
import com.epam.architecture.entities.Book;
import com.epam.architecture.exceptions.HistoryException;
import com.epam.architecture.library.LiteratureManager;
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
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class LibraryService {
    public static final Logger logger = LogManager.getLogger();
    public static final Properties properties = new Properties();
    public static final File historyFile = new File(properties.getProperty("historyFile"));
    private final AccountManager accountManager = new AccountManager();
    private final LiteratureManager literatureManager = new LiteratureManager();
    public static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    {
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream("src/main/resources/source.properties"))) {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        Set<String> booksISBNWithUserBookmarks = accountManager.takeBooksWithCurrentUserBookmarks();
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

    public void closeSourceService(){
        sessionFactory.close();
    }
}
