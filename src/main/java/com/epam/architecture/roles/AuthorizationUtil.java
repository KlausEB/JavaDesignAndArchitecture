package com.epam.architecture.roles;

import jakarta.xml.soap.SOAPException;
import jakarta.xml.ws.WebServiceContext;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;

import java.util.Set;

public class AuthorizationUtil {
    public static boolean isAuthorizedRequest(SOAPMessageContext messageContext, Set<RoleEnum> roles) throws SOAPException {
//        SOAPHeader soapHeader = messageContext.getMessage().getSOAPHeader();
//        Iterator<SOAPHeaderElement> iterator = soapHeader.examineHeaderElements(SOAPConstants.URI_SOAP_ACTOR_NEXT);
//        String login = null;
//        String password = null;
//        while (iterator.hasNext()) {
//            Node node = iterator.next();
//            if (node.getNodeName().equals("login")) {
//                login = node.getValue();
//            }
//            if (node.getNodeName().equals("password")) {
//                password = node.getValue();
//            }
//        }
//        if (login != null && password != null) {
//            LibraryService libraryService = LibraryService.getInstanceWithDeserializeData();
//            return libraryService.logInAccount(login, password) && roles.contains(libraryService.userRole(login));
//        }
//        throw new NotAuthorizedException("Not found login and password");
        return true;
    }

    public static String getLoginFromRequest(WebServiceContext context) {
//        SOAPMessage soapMessage = ((AbstractWebServiceContext) context).getRequestPacket().getMessage().readAsSOAPMessage();
//        SOAPEnvelope soapEnv = soapMessage.getSOAPPart().getEnvelope();
//        SOAPHeader soapHeader = soapEnv.getHeader();
//        Iterator<SOAPHeaderElement> headerElements = soapHeader.examineHeaderElements(SOAPConstants.URI_SOAP_ACTOR_NEXT);
//        while (headerElements.hasNext()) {
//            Node node = headerElements.next();
//            if (node.getNodeName().equals("login")) {
//                return node.getValue();
//            }
//        }
//        throw new NotAuthorizedException("Not found login");

        return "Nikolai";
    }
}
