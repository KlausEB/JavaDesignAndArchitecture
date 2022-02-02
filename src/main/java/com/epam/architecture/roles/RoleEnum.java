package com.epam.architecture.roles;

import jakarta.xml.soap.SOAPException;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;

public enum RoleEnum {
    USER(new UserRole()),
    ADMIN(new AdminRole());

    private LibraryRole role;

    RoleEnum(LibraryRole role) {
        this.role = role;
    }

    public boolean identifyUserRole(SOAPMessageContext messageContext) throws SOAPException {
        return role.isUserInRole(messageContext);
    }

    public String getAuthorizedUserRole(SOAPMessageContext messageContext) throws SOAPException {
        return role.getAuthorizedUserRole(messageContext);
    }
}
