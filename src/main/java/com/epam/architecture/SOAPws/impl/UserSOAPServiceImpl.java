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

    private LibraryService libraryService = LibraryWebWorker.takeLibraryService();

    //IT'S NOT WORK!!!!
    @Resource
    private WebServiceContext webServiceContext;

    @Override
    public boolean addBookmark(String isbn, int pageNumber) {
        String login = getCurrentUserLogin();
        return libraryService.addBookmark(login, isbn, pageNumber);
    }

    @Override
    public boolean deleteBookmark(String isbn, int pageNumber) {
        String login = getCurrentUserLogin();
        return libraryService.deleteBookmark(login, isbn, pageNumber);
    }

    @Override
    public Book[] booksWithUserBookmarks() {
        String login = getCurrentUserLogin();
        return libraryService.booksWithUserBookmarks(login).toArray(Book[]::new);
    }

    @Override
    public void save() {
        libraryService.requestSerializeData();
    }

    //HERE
    public String getCurrentUserLogin() {
        MessageContext mctx = webServiceContext.getMessageContext();
        Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
        List userList = (List) http_headers.get("login");
        return (String) userList.get(0);
    }
}
