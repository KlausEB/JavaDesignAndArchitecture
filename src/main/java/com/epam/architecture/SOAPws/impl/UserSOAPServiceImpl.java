package com.epam.architecture.SOAPws.impl;

import com.epam.architecture.SOAPws.UserSOAPService;
import com.epam.architecture.SOAPws.util.LibraryWebWorker;
import com.epam.architecture.entities.Book;
import com.epam.architecture.userinterface.LibraryService;
import jakarta.annotation.PreDestroy;
import jakarta.jws.HandlerChain;
import jakarta.jws.WebService;

@HandlerChain(file = "../user-handler.xml")
@WebService(endpointInterface = "com.epam.architecture.SOAPws.UserSOAPService")
public class UserSOAPServiceImpl implements UserSOAPService {

    private LibraryService libraryService = LibraryWebWorker.takeLibraryService();

    @Override
    public boolean addBookmark(String login, String isbn, int pageNumber) {
        return libraryService.addBookmark(login, isbn, pageNumber);
    }

    @Override
    public boolean deleteBookmark(String login, String isbn, int pageNumber) {
        return libraryService.deleteBookmark(login, isbn, pageNumber);
    }

    @Override
    public Book[] booksWithUserBookmarks(String login) {
        return libraryService.booksWithUserBookmarks(login).toArray(Book[]::new);
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
