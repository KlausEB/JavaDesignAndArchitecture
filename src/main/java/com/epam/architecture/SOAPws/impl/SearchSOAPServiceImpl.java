package com.epam.architecture.SOAPws.impl;

import com.epam.architecture.SOAPws.SearchSOAPService;
import com.epam.architecture.SOAPws.util.LibraryWebWorker;
import com.epam.architecture.entities.Book;
import com.epam.architecture.userinterface.LibraryService;
import jakarta.annotation.PreDestroy;
import jakarta.jws.HandlerChain;
import jakarta.jws.WebService;

@HandlerChain(file = "../user-handler.xml")
@WebService(endpointInterface = "com.epam.architecture.SOAPws.SearchSOAPService")
public class SearchSOAPServiceImpl implements SearchSOAPService {

    private LibraryService libraryService = LibraryWebWorker.takeLibraryService();

    @Override
    public Book[] booksByPartAuthorName(String partName) {
        return libraryService.booksByPartAuthorName(partName).toArray(Book[]::new);
    }

    @Override
    public Book[] booksByPartName(String partName) {
        return libraryService.booksByPartName(partName).toArray(Book[]::new);
    }

    @Override
    public Book bookByISBN(String isbn) {
        return libraryService.bookByISBN(isbn);
    }

    @Override
    public Book[] booksByYearRange(int minYear, int maxYear) {
        return libraryService.booksByYearRange(minYear, maxYear).toArray(Book[]::new);
    }

    @Override
    public Book[] booksByYearPagesPartName(int yearOfPublishing, int numberOfPages, String partName) {
        return libraryService.booksByYearPagesPartName(yearOfPublishing, numberOfPages, partName).toArray(Book[]::new);
    }

    @PreDestroy
    private void destroy() {
        libraryService.closeSourceService();
    }
}
