package com.epam.architecture.SOAPws.impl;

import com.epam.architecture.SOAPws.AdminSOAPService;
import com.epam.architecture.SOAPws.util.LibraryWebWorker;
import com.epam.architecture.userinterface.LibraryService;
import jakarta.jws.HandlerChain;
import jakarta.jws.WebService;

@HandlerChain(file = "../admin-handler.xml")
@WebService(endpointInterface = "com.epam.architecture.SOAPws.AdminSOAPService")
public class AdminSOAPServiceImpl implements AdminSOAPService {

    private LibraryService libraryService = LibraryWebWorker.takeLibraryService();

    @Override
    public boolean addNewUser(String login, String password) {
        return libraryService.addNewUser(login, password);
    }

    @Override
    public boolean banUser(String login) {
        return libraryService.banUser(login);
    }

    @Override
    public String takeHistory() {
        return libraryService.takeHistory();
    }

    @Override
    public void save() {
        libraryService.requestSerializeData();
    }
}
