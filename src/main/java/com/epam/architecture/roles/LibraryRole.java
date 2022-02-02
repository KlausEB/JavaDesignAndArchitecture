package com.epam.architecture.roles;

import jakarta.xml.soap.SOAPException;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;

public interface LibraryRole {
    boolean isUserInRole(SOAPMessageContext messageContext) throws SOAPException;

    String getAuthorizedUserRole(SOAPMessageContext messageContext) throws SOAPException;
}
