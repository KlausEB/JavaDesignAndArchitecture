package com.epam.designAndArchitecture.userInterface;

import com.epam.designAndArchitecture.account.AccountManager;
import com.epam.designAndArchitecture.library.LiteratureManager;

import java.util.List;

public class LibraryService {
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

    public List<String> requestBooksByPartAuthorName(String partName) {
        return literatureManager.searchBooksByPartAuthorName(partName);
    }

    public List<String> requestBooksByPartName(String partName) {
        return literatureManager.searchBooksByPartName(partName);
    }

    public String requestBookByISBN(String isbn) {
        return literatureManager.searchBookByISBN(isbn);
    }

    public List<String> requestBooksByYearRange(int minYear, int maxYear) {
        return literatureManager.searchBooksByYearRange(minYear, maxYear);
    }

    public List<String> requestByYearPagesPartName(int yearOfPublishing, int numberOfPages, String partName){
        return literatureManager.searchBooksByYearPagesPartName(yearOfPublishing, numberOfPages, partName);
    }
}
