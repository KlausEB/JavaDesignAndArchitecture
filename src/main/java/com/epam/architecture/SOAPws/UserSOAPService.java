package com.epam.architecture.SOAPws;

import com.epam.architecture.entities.Book;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface UserSOAPService {

    @WebMethod
    boolean addBookmark(String isbn, int pageNumber);

    @WebMethod
    boolean deleteBookmark(String isbn, int pageNumber);

    @WebMethod
    Book[] booksWithUserBookmarks();

    @WebMethod
    void save();
}
