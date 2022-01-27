package com.epam.architecture.SOAPws.impl;

import com.epam.architecture.SOAPws.UserSOAPService;
import com.epam.architecture.entities.Book;
import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.util.LibraryWebWorker;
import jakarta.annotation.Resource;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceContext;
import jakarta.xml.ws.handler.MessageContext;

import java.util.List;
import java.util.Map;

@WebService(endpointInterface = "com.epam.architecture.SOAPws.UserSOAPService")
public class UserSOAPServiceImpl implements UserSOAPService {
    @Resource
    WebServiceContext webServiceContext;

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
        String login = getCurrentUserLogin();
        return libraryService.addBookmark(login, isbn, pageNumber);
    }

    @Override
    public boolean deleteBookmark(String isbn, int pageNumber) {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        String login = getCurrentUserLogin();
        return libraryService.deleteBookmark(login, isbn, pageNumber);
    }

    @Override
    public List<Book> booksWithUserBookmarks() {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        String login = getCurrentUserLogin();
        return libraryService.booksWithUserBookmarks(login);
    }

    @Override
    public void save() {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        libraryService.requestSerializeData();
    }

    public String getCurrentUserLogin() {
        MessageContext messageContext = webServiceContext.getMessageContext();

        Map<String, List<String>> http_headers = (Map<String, List<String>>) messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);
        List<String> userList = http_headers.get("Username");
        return userList.get(0);
    }
}
