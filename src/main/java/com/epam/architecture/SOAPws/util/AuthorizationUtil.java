package com.epam.architecture.SOAPws.util;

import com.epam.architecture.userinterface.LibraryService;
import jakarta.xml.soap.*;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import jakarta.xml.ws.soap.SOAPFaultException;

import javax.xml.namespace.QName;
import java.util.Iterator;

public class AuthorizationUtil {
    public static String isAuthorized(SOAPMessageContext messageContext) throws SOAPException {
        SOAPHeader soapHeader = messageContext.getMessage().getSOAPHeader();
        Iterator<SOAPHeaderElement> iterator = soapHeader.extractHeaderElements(SOAPConstants.URI_SOAP_ACTOR_NEXT);
        String login = null;
        String password = null;
        while (iterator.hasNext()) {
            Node node = iterator.next();
            if (node.getNodeName().equals("login")) {
                login = node.getValue();
            }
            if (node.getNodeName().equals("password")) {
                password = node.getValue();
            }
        }
        if (login != null && password != null) {
            LibraryService libraryService = LibraryWebWorker.takeLibraryService();
            if (libraryService.logInAccount(login, password)) {
                return login;
            }
        }

        SOAPFactory soapFactory = SOAPFactory.newInstance();
        SOAPFault soapFault = soapFactory.createFault(
                "Not correct login or password",
                new QName("http://schemas.xmlsoap.org/soap/envelope/", "Client"));
        throw new SOAPFaultException(soapFault);
    }
}
