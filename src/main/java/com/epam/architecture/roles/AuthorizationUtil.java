package com.epam.architecture.roles;

import com.epam.architecture.userinterface.LibraryService;
import com.sun.xml.ws.server.AbstractWebServiceContext;
import jakarta.xml.soap.*;
import jakarta.xml.ws.WebServiceContext;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import jakarta.xml.ws.soap.SOAPFaultException;

import java.util.Iterator;
import java.util.Set;

public class AuthorizationUtil {
    public static boolean isAuthorizedRequest(SOAPMessageContext messageContext, Set<RoleEnum> roles) throws SOAPException {
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
            return libraryService.logInAccount(login, password) && roles.contains(libraryService.userRole(login));
        }
        SOAPBody soapBody = messageContext.getMessage().getSOAPPart().getEnvelope().getBody();
        SOAPFault soapFault = soapBody.addFault();
        soapFault.setFaultString("Not correct login or password");
        throw new SOAPFaultException(soapFault);
    }

    public static String getLoginFromRequest(WebServiceContext context) throws SOAPException {
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
        soapFault.setFaultString("Service does not work without a login");
        throw new SOAPFaultException(soapFault);
    }
}
