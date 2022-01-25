package com.epam.architecture.SOAPws;

import com.epam.architecture.userinterface.LibraryService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService(endpointInterface = "com.epam.architecture.SOAPws.LibrarySOAPService")
public class LibrarySOAPServiceImpl implements LibrarySOAPService{
    private final LibraryService libraryService = new LibraryService();

    @Override
    public boolean signUpAccount(String login, String password) {
        return libraryService.signUpAccount(login, password);
    }

    @Override
    public boolean logInAccount(String login, String password) {
        return libraryService.logInAccount(login, password);
    }

    @Override
    public boolean appendBook(String authorName,
                              String bookName, int yearOfPublishing, int numberOfPages, String bookISBN) {
        return libraryService.appendBook(authorName, bookName, yearOfPublishing, numberOfPages, bookISBN);
    }

    @Override
    public boolean deleteBook(String bookISBN) {
        return libraryService.deleteBook(bookISBN);
    }

    @Override
    public boolean appendAuthor(String authorName) {
        return libraryService.deleteBook(authorName);
    }

    @Override
    public boolean deleteAuthor(String authorName) {
        return libraryService.deleteAuthor(authorName);
    }

    @Override
    public String booksByPartAuthorName(String partName) {
        return libraryService.booksByPartAuthorName(partName);
    }

    @Override
    public String booksByPartName(String partName) {
        return libraryService.booksByPartName(partName);
    }

    @Override
    public String bookByISBN(String isbn) {
        return libraryService.bookByISBN(isbn);
    }

    @Override
    public String booksByYearRange(int minYear, int maxYear) {
        return libraryService.booksByYearRange(minYear, maxYear);
    }

    @Override
    public String booksByYearPagesPartName(int yearOfPublishing, int numberOfPages, String partName) {
        return libraryService.booksByYearPagesPartName(yearOfPublishing, numberOfPages, partName);
    }

    @Override
    public boolean appendBookmark(String isbn, int pageNumber) {
        return libraryService.appendBookmark(isbn, pageNumber);
    }

    @Override
    public boolean deleteBookmark(String isbn, int pageNumber) {
        return libraryService.deleteBookmark(isbn, pageNumber);
    }

    @Override
    public String booksWithUserBookmarks() {
        return libraryService.booksWithUserBookmarks();
    }

    @Override
    public boolean appendNewUser(String login, String password) {
        return libraryService.appendNewUser(login, password);
    }

    @Override
    public boolean banUser(String login) {
        return libraryService.banUser(login);
    }

    @Override
    public String takeHistory() {
        return libraryService.takeHistory();
    }

    @Override
    public void save() {
        libraryService.requestSerializeData();
    }
}
