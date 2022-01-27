package com.epam.architecture.SOAPws.impl;

import com.epam.architecture.SOAPws.SearchSOAPService;
import com.epam.architecture.entities.Book;
import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.util.LibraryWebWorker;
import jakarta.jws.WebService;

import java.util.List;

@WebService(endpointInterface = "com.epam.architecture.SOAPws.SearchSOAPService")
public class SearchSOAPServiceImpl implements SearchSOAPService {
    @Override
    public List<Book> booksByPartAuthorName(String partName) {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.booksByPartAuthorName(partName);
    }

    @Override
    public List<Book> booksByPartName(String partName) {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.booksByPartName(partName);
    }

    @Override
    public Book bookByISBN(String isbn) {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.bookByISBN(isbn);
    }

    @Override
    public List<Book> booksByYearRange(int minYear, int maxYear) {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.booksByYearRange(minYear, maxYear);
    }

    @Override
    public List<Book> booksByYearPagesPartName(int yearOfPublishing, int numberOfPages, String partName) {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.booksByYearPagesPartName(yearOfPublishing, numberOfPages, partName);
    }
}
