package com.epam.architecture.SOAPws.impl;

import com.epam.architecture.SOAPws.UserSOAPService;
import com.epam.architecture.entities.Book;
import com.epam.architecture.roles.AuthorizationUtil;
import com.epam.architecture.userinterface.LibraryService;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import jakarta.jws.HandlerChain;
import jakarta.jws.WebService;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.ws.WebServiceContext;

@HandlerChain(file = "../user-handler.xml")
@WebService(endpointInterface = "com.epam.architecture.SOAPws.UserSOAPService")
public class UserSOAPServiceImpl implements UserSOAPService {

    @Resource
    private WebServiceContext context;

    private LibraryService libraryService = LibraryService.getInstanceWithDeserializeData();

    @Override
    public boolean addBookmark(String isbn, int pageNumber) throws SOAPException {
        return libraryService.addBookmark(AuthorizationUtil.getLoginFromRequest(context), isbn, pageNumber);
    }

    @Override
    public boolean deleteBookmark(String isbn, int pageNumber) throws SOAPException {
        return libraryService.deleteBookmark(AuthorizationUtil.getLoginFromRequest(context), isbn, pageNumber);
    }

    @Override
    public Book[] booksWithUserBookmarks() throws SOAPException {
        return libraryService.booksWithUserBookmarks(AuthorizationUtil.getLoginFromRequest(context)).toArray(Book[]::new);
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
