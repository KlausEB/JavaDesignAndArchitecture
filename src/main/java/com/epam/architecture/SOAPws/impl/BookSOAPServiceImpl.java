package com.epam.architecture.SOAPws.impl;

import com.epam.architecture.SOAPws.BookSOAPService;
import com.epam.architecture.userinterface.LibraryService;
import jakarta.annotation.PreDestroy;
import jakarta.jws.HandlerChain;
import jakarta.jws.WebService;

@HandlerChain(file = "../user-handler.xml")
@WebService(endpointInterface = "com.epam.architecture.SOAPws.BookSOAPService")
public class BookSOAPServiceImpl implements BookSOAPService {

    private LibraryService libraryService = LibraryService.getInstanceWithDeserializeData();

    @Override
    public boolean addBook(String authorName,
                           String bookName, int yearOfPublishing, int numberOfPages, String bookISBN) {
        return libraryService.appendBook(authorName, bookName, yearOfPublishing, numberOfPages, bookISBN);
    }

    @Override
    public boolean deleteBook(String bookISBN) {
        return libraryService.deleteBook(bookISBN);
    }

    @Override
    public boolean addAuthor(String authorName) {
        return libraryService.appendAuthor(authorName);
    }

    @Override
    public boolean deleteAuthor(String authorName) {
        return libraryService.deleteAuthor(authorName);
    }

    @Override
    public void save() {
        libraryService.requestSerializeData();
    }

    @PreDestroy
    private void destroy() {
        libraryService.closeSourceService();
    }
}