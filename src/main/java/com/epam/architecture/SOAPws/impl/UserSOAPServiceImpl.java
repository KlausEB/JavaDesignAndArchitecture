package com.epam.architecture.SOAPws.impl;

import com.epam.architecture.SOAPws.UserSOAPService;
import com.epam.architecture.SOAPws.util.LibraryWebWorker;
import com.epam.architecture.entities.Book;
import com.epam.architecture.userinterface.LibraryService;
import com.sun.xml.ws.server.AbstractWebServiceContext;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import jakarta.jws.HandlerChain;
import jakarta.jws.WebService;
import jakarta.xml.soap.*;
import jakarta.xml.ws.WebServiceContext;
import jakarta.xml.ws.soap.SOAPFaultException;

import java.util.Iterator;

@HandlerChain(file = "../user-handler.xml")
@WebService(endpointInterface = "com.epam.architecture.SOAPws.UserSOAPService")
public class UserSOAPServiceImpl implements UserSOAPService {

    @Resource
    private WebServiceContext context;

    private LibraryService libraryService = LibraryWebWorker.takeLibraryService();

    @Override
    public boolean addBookmark(String isbn, int pageNumber) throws SOAPException {
        return libraryService.addBookmark(getLogin(), isbn, pageNumber);
    }

    @Override
    public boolean deleteBookmark(String isbn, int pageNumber) throws SOAPException {
        return libraryService.deleteBookmark(getLogin(), isbn, pageNumber);
    }

    @Override
    public Book[] booksWithUserBookmarks() throws SOAPException {
        return libraryService.booksWithUserBookmarks(getLogin()).toArray(Book[]::new);
    }

    @Override
    public void save() {
        libraryService.requestSerializeData();
    }

    @PreDestroy
    private void destroy() {
        libraryService.closeSourceService();
    }

    public String getLogin() throws SOAPException {
        SOAPMessage soapMessage = ((AbstractWebServiceContext) context).getRequestPacket().getMessage().readAsSOAPMessage();
        SOAPEnvelope soapEnv = soapMessage.getSOAPPart().getEnvelope();
        SOAPHeader soapHeader = soapEnv.getHeader();
        Iterator<SOAPHeaderElement> headerElements = soapHeader.examineHeaderElements(SOAPConstants.URI_SOAP_ACTOR_NEXT);
        while (headerElements.hasNext()) {
            Node node = headerElements.next();
            if (node.getNodeName().equals("login")) {
                return node.getValue();
            }
        }
        SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
        SOAPFault soapFault = soapBody.addFault();
        soapFault.setFaultString("User Service does not work without a login");
        throw new SOAPFaultException(soapFault);
    }
}
