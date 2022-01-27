package com.epam.architecture.SOAPws.impl;

import com.epam.architecture.SOAPws.SearchSOAPService;
import com.epam.architecture.SOAPws.util.LibraryWebWorker;
import com.epam.architecture.entities.Book;
import com.epam.architecture.userinterface.LibraryService;
import jakarta.jws.WebService;


@WebService(endpointInterface = "com.epam.architecture.SOAPws.SearchSOAPService")
public class SearchSOAPServiceImpl implements SearchSOAPService {
    @Override
    public Book[] booksByPartAuthorName(String partName) {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.booksByPartAuthorName(partName).toArray(Book[]::new);
    }

    @Override
    public Book[] booksByPartName(String partName) {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.booksByPartName(partName).toArray(Book[]::new);
    }

    @Override
    public Book bookByISBN(String isbn) {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.bookByISBN(isbn);
    }

    @Override
    public Book[] booksByYearRange(int minYear, int maxYear) {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.booksByYearRange(minYear, maxYear).toArray(Book[]::new);
    }

    @Override
    public Book[] booksByYearPagesPartName(int yearOfPublishing, int numberOfPages, String partName) {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.booksByYearPagesPartName(yearOfPublishing, numberOfPages, partName).toArray(Book[]::new);
    }
}
