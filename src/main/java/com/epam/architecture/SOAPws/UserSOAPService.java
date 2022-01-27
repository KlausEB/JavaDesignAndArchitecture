package com.epam.architecture.SOAPws;

import com.epam.architecture.entities.Book;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface UserSOAPService {

    @WebMethod
    boolean logInAccount(String login, String password);

    @WebMethod
    boolean signUpAccount(String login, String password);

    @WebMethod
    boolean addBookmark(String isbn, int pageNumber);

    @WebMethod
    boolean deleteBookmark(String isbn, int pageNumber);

    @WebMethod
    List<Book> booksWithUserBookmarks();

    @WebMethod
    void save();
}
