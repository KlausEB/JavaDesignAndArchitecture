package com.epam.architecture.SOAPws.handlers;

import com.epam.architecture.SOAPws.util.AuthorizationUtil;
import com.epam.architecture.userinterface.LibraryService;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;

import javax.xml.namespace.QName;
import java.util.Collections;
import java.util.Set;

public class UserHandler implements SOAPHandler<SOAPMessageContext> {
    @Override
    public Set<QName> getHeaders() {
        return Collections.emptySet();
    }

    @Override
    public boolean handleMessage(SOAPMessageContext soapMessageContext) {
        if (!(boolean) soapMessageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)) {
            try {
                AuthorizationUtil.isAuthorized(soapMessageContext);
                return true;
            } catch (SOAPException e) {
                LibraryService.logger.error("Not found header");
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext soapMessageContext) {
        return false;
    }

    @Override
    public void close(MessageContext messageContext) {
    }
}
