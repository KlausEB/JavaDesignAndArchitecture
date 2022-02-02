package com.epam.architecture.roles;

import com.epam.architecture.userinterface.LibraryService;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPFault;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import jakarta.xml.ws.soap.SOAPFaultException;

public class AdminRole implements LibraryRole {

    @Override
    public boolean isUserInRole(SOAPMessageContext messageContext) throws SOAPException {
        return getAuthorizedUserRole(messageContext) != null;
    }

    @Override
    public String getAuthorizedUserRole(SOAPMessageContext messageContext) throws SOAPException {
        String login = RoleEnum.USER.getAuthorizedUserRole(messageContext);
        LibraryService libraryService = LibraryService.getInstanceWithDeserializeData();
        if (libraryService.userRole(login) == RoleEnum.ADMIN) {
            return login;
        }
        SOAPBody soapBody = messageContext.getMessage().getSOAPPart().getEnvelope().getBody();
        SOAPFault soapFault = soapBody.addFault();
        soapFault.setFaultString("User is not admin");
        throw new SOAPFaultException(soapFault);
    }
}
