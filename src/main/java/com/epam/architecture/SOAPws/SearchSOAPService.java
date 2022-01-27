package com.epam.architecture.SOAPws;

import com.epam.architecture.entities.Book;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface SearchSOAPService {
    @WebMethod
    Book[] booksByPartAuthorName(String partName);

    @WebMethod
    Book[] booksByPartName(String partName);

    @WebMethod
    Book bookByISBN(String isbn);

    @WebMethod
    Book[] booksByYearRange(int minYear, int maxYear);

    @WebMethod
    Book[] booksByYearPagesPartName(int yearOfPublishing, int numberOfPages, String partName);
}
