package com.epam.architecture.SOAPws;

import com.epam.architecture.entities.Book;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface SearchSOAPService {
    @WebMethod
    List<Book> booksByPartAuthorName(String partName);

    @WebMethod
    List<Book> booksByPartName(String partName);

    @WebMethod
    Book bookByISBN(String isbn);

    @WebMethod
    List<Book> booksByYearRange(int minYear, int maxYear);

    @WebMethod
    List<Book> booksByYearPagesPartName(int yearOfPublishing, int numberOfPages, String partName);
}
