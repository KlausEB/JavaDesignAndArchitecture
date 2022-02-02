package com.epam.architecture.roles;

import com.epam.architecture.userinterface.LibraryService;
import jakarta.xml.soap.*;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import jakarta.xml.ws.soap.SOAPFaultException;

import java.util.Iterator;

public class UserRole implements LibraryRole {
    @Override
    public boolean isUserInRole(SOAPMessageContext messageContext) throws SOAPException {
        return getAuthorizedUserRole(messageContext) != null;
    }

    @Override
    public String getAuthorizedUserRole(SOAPMessageContext messageContext) throws SOAPException {
        SOAPHeader soapHeader = messageContext.getMessage().getSOAPHeader();
        Iterator<SOAPHeaderElement> iterator = soapHeader.examineHeaderElements(SOAPConstants.URI_SOAP_ACTOR_NEXT);
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
            LibraryService libraryService = LibraryService.getInstanceWithDeserializeData();
            if (libraryService.logInAccount(login, password)) {
                return login;
            }
        }
        SOAPBody soapBody = messageContext.getMessage().getSOAPPart().getEnvelope().getBody();
        SOAPFault soapFault = soapBody.addFault();
        soapFault.setFaultString("Not correct login or password");
        throw new SOAPFaultException(soapFault);
    }
}
