package com.epam.architecture.SOAPws.impl;

import com.epam.architecture.SOAPws.UserSOAPService;
import com.epam.architecture.SOAPws.util.LibraryWebWorker;
import com.epam.architecture.entities.Book;
import com.epam.architecture.userinterface.LibraryService;
import jakarta.annotation.Resource;
import jakarta.jws.HandlerChain;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceContext;
import jakarta.xml.ws.handler.MessageContext;

import java.util.List;
import java.util.Map;

@HandlerChain(file = "../user-handler.xml")
@WebService(endpointInterface = "com.epam.architecture.SOAPws.UserSOAPService")
public class UserSOAPServiceImpl implements UserSOAPService {
    @Resource
    WebServiceContext webServiceContext;

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
    public Book[] booksWithUserBookmarks() {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        String login = getCurrentUserLogin();
        return libraryService.booksWithUserBookmarks(login).toArray(Book[]::new);
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
