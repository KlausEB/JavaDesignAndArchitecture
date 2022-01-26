package com.epam.architecture.SOAPws.impl;

import com.epam.architecture.SOAPws.UserSOAPService;
import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.util.LibraryWebWorker;
import jakarta.jws.WebService;

@WebService(endpointInterface = "com.epam.architecture.SOAPws.UserSOAPService")
public class UserSOAPServiceImpl implements UserSOAPService {

    @Override
    public boolean logInAccount(String login, String password) {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.logInAccount(login, password);
    }

    @Override
    public boolean signUpAccount(String login, String password) {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.signUpAccount(login, password);
    }

    @Override
    public boolean addBookmark(String isbn, int pageNumber) {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.appendBookmark(isbn, pageNumber);
    }

    @Override
    public boolean deleteBookmark(String isbn, int pageNumber) {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.deleteBookmark(isbn, pageNumber);
    }

    @Override
    public String booksWithUserBookmarks() {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.booksWithUserBookmarks();
    }

    @Override
    public void save() {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        libraryService.requestSerializeData();
    }
}
