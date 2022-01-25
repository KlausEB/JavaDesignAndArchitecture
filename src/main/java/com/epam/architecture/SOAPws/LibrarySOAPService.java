package com.epam.architecture.SOAPws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface LibrarySOAPService {

    @WebMethod(operationName = "signUpAccount")
    boolean signUpAccount(String login, String password);

    @WebMethod
    boolean logInAccount(String login, String password);

    @WebMethod
    boolean appendBook(String authorName,
                              String bookName, int yearOfPublishing, int numberOfPages, String bookISBN);

    @WebMethod
    boolean deleteBook(String bookISBN);

    @WebMethod
    boolean appendAuthor(String authorName);

    @WebMethod
    boolean deleteAuthor(String authorName);

    @WebMethod
    String booksByPartAuthorName(String partName);

    @WebMethod
    String booksByPartName(String partName);

    @WebMethod
    String bookByISBN(String isbn);

    @WebMethod
    String booksByYearRange(int minYear, int maxYear);

    @WebMethod
    String booksByYearPagesPartName(int yearOfPublishing, int numberOfPages, String partName);

    @WebMethod
    boolean appendBookmark(String isbn, int pageNumber);

    @WebMethod
    boolean deleteBookmark(String isbn, int pageNumber);

    @WebMethod
    String booksWithUserBookmarks();

    @WebMethod
    boolean appendNewUser(String login, String password);

    @WebMethod
    boolean banUser(String login);

    @WebMethod
    String takeHistory();

    @WebMethod
    void save();
}
