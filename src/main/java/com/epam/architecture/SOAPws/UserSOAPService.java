package com.epam.architecture.SOAPws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

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
    String booksWithUserBookmarks();

    @WebMethod
    void save();
}
