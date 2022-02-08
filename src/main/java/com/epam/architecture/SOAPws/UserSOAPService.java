package com.epam.architecture.SOAPws;

import com.epam.architecture.entities.Book;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.soap.SOAPException;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface UserSOAPService {

    @WebMethod
    boolean addBookmark(String isbn, int pageNumber) throws SOAPException;

    @WebMethod
    boolean deleteBookmark(String isbn, int pageNumber) throws SOAPException;

    @WebMethod
    Book[] booksWithUserBookmarks() throws SOAPException;

    @WebMethod
    void save();
}
