package com.epam.architecture.SOAPws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface SearchSOAPService {
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
}
