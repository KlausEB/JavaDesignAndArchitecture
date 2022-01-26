package com.epam.architecture.SOAPws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface AdminSOAPService {
    @WebMethod
    boolean addNewUser(String login, String password);

    @WebMethod
    boolean banUser(String login);

    @WebMethod
    String takeHistory();

    @WebMethod
    void save();
}
