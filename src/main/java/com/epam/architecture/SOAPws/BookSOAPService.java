package com.epam.architecture.SOAPws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface BookSOAPService {
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
    void save();
}
