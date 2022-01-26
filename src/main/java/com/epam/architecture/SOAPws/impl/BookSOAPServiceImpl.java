package com.epam.architecture.SOAPws.impl;

import com.epam.architecture.SOAPws.BookSOAPService;
import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.util.LibraryWebWorker;
import jakarta.jws.WebService;

@WebService(endpointInterface = "com.epam.architecture.SOAPws.BookSOAPService")
public class BookSOAPServiceImpl implements BookSOAPService {
    @Override
    public boolean addBook(String authorName,
                           String bookName, int yearOfPublishing, int numberOfPages, String bookISBN) {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.appendBook(authorName, bookName, yearOfPublishing, numberOfPages, bookISBN);
    }

    @Override
    public boolean deleteBook(String bookISBN) {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.deleteBook(bookISBN);
    }

    @Override
    public boolean addAuthor(String authorName) {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.deleteBook(authorName);
    }

    @Override
    public boolean deleteAuthor(String authorName) {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.deleteAuthor(authorName);
    }

    @Override
    public void save() {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        libraryService.requestSerializeData();
    }
}
