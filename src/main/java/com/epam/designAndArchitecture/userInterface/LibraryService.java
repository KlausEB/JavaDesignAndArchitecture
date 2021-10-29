package com.epam.designAndArchitecture.userInterface;

import com.epam.designAndArchitecture.account.AccountManager;
import com.epam.designAndArchitecture.entities.Book;
import com.epam.designAndArchitecture.library.LiteratureManager;
import com.epam.designAndArchitecture.util.BookmarkService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class LibraryService {
    public static File historyFile = new File("history/ActionHistory.txt");
    private final AccountManager accountManager = new AccountManager();
    private final LiteratureManager literatureManager = new LiteratureManager();

    public boolean requestSignUpAccount(String login, String password) {
        return accountManager.signUpUser(login, password);
    }

    public boolean requestLogInAccount(String login, String password) {
        return accountManager.logInUser(login, password);
    }

    public boolean requestAppendBook(String authorName,
                                     String bookName, int yearOfPublishing, int numberOfPages, String bookISBN) {
        return literatureManager.appendBook(authorName,
                bookName, yearOfPublishing, numberOfPages, bookISBN);
    }

    public boolean requestDeleteBook(String bookISBN) {
        return literatureManager.deleteBook(bookISBN);
    }

    public boolean requestAppendAuthor(String authorName) {
        return literatureManager.appendAuthor(authorName);
    }

    public boolean requestDeleteAuthor(String authorName) {
        return literatureManager.deleteAuthor(authorName);
    }

    public String convertCollectionToString(Collection<?> objects) {
        StringBuilder stringObjects = new StringBuilder();
        for (Object currentObject : objects) {
            stringObjects.append(currentObject.toString()).append('\n');
        }
        return stringObjects.toString();
    }

    public String requestBooksByPartAuthorName(String partName) {
        List<Book> booksByPartAuthorName = literatureManager.searchBooksByPartAuthorName(partName);
        return convertCollectionToString(booksByPartAuthorName);
    }

    public String requestBooksByPartName(String partName) {
        List<Book> booksByPartName = literatureManager.searchBooksByPartName(partName);
        return convertCollectionToString(booksByPartName);
    }

    public String requestBookByISBN(String isbn) {
        return literatureManager.searchBookByISBN(isbn).toString();
    }

    public String requestBooksByYearRange(int minYear, int maxYear) {
        List<Book> booksByYearRange = literatureManager.searchBooksByYearRange(minYear, maxYear);
        return convertCollectionToString(booksByYearRange);
    }

    public String requestByYearPagesPartName(int yearOfPublishing, int numberOfPages, String partName) {
        List<Book> booksByYearPagesPartName = literatureManager.searchBooksByYearPagesPartName(yearOfPublishing, numberOfPages, partName);
        return convertCollectionToString(booksByYearPagesPartName);
    }

    public boolean requestAppendBookmark(String isbn, int pageNumber) {
        Book bookByISBN = literatureManager.searchBookByISBN(isbn);
        if (bookByISBN == null) {
            return false;
        }
        BookmarkService bookmarkService = accountManager.getCurrentBookmarks();
        return bookmarkService.appendBookmark(bookByISBN, pageNumber);
    }

    public boolean requestDeleteBookmark(String isbn, int pageNumber) {
        Book bookByISBN = literatureManager.searchBookByISBN(isbn);
        if (bookByISBN == null) {
            return false;
        }
        BookmarkService bookmarkService = accountManager.getCurrentBookmarks();
        return bookmarkService.deleteBookmark(bookByISBN, pageNumber);
    }

    public String requestBooksWithUserBookmarks() {
        BookmarkService bookmarkService = accountManager.getCurrentBookmarks();
        Set<Book> booksWithUserBookmarks = bookmarkService.takeBooksWithBookmarks();
        return convertCollectionToString(booksWithUserBookmarks);
    }

    public boolean requestAppendNewUser(String login, String password) {
        return accountManager.adminAppendAccount(login, password);
    }

    public boolean requestBanUser(String login) {
        return accountManager.deleteUser(login);
    }

    public String requestTakeHistory() throws IOException {
        if (historyFile.exists() && accountManager.isAdmin()) {
            return Files.readString(Paths.get(historyFile.getPath()));
        }
        return "You cannot read history";
    }
}
