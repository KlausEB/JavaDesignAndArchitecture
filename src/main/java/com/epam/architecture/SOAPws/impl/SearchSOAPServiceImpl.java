package com.epam.architecture.SOAPws.impl;

import com.epam.architecture.SOAPws.SearchSOAPService;
import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.util.LibraryWebWorker;
import jakarta.jws.WebService;

@WebService(endpointInterface = "com.epam.architecture.SOAPws.SearchSOAPService")
public class SearchSOAPServiceImpl implements SearchSOAPService {
    @Override
    public String booksByPartAuthorName(String partName) {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.booksByPartAuthorName(partName);
    }

    @Override
    public String booksByPartName(String partName) {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.booksByPartName(partName);
    }

    @Override
    public String bookByISBN(String isbn) {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.bookByISBN(isbn);
    }

    @Override
    public String booksByYearRange(int minYear, int maxYear) {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.booksByYearRange(minYear, maxYear);
    }

    @Override
    public String booksByYearPagesPartName(int yearOfPublishing, int numberOfPages, String partName) {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.booksByYearPagesPartName(yearOfPublishing, numberOfPages, partName);
    }
}
