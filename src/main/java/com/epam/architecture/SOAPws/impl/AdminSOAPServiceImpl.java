package com.epam.architecture.SOAPws.impl;

import com.epam.architecture.SOAPws.AdminSOAPService;
import com.epam.architecture.userinterface.LibraryService;
import com.epam.architecture.util.LibraryWebWorker;
import jakarta.jws.WebService;

@WebService(endpointInterface = "com.epam.architecture.SOAPws.AdminSOAPService")
public class AdminSOAPServiceImpl implements AdminSOAPService {

    @Override
    public boolean addNewUser(String login, String password) {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.addNewUser(login, password);
    }

    @Override
    public boolean banUser(String login) {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.banUser(login);
    }

    @Override
    public String takeHistory() {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        return libraryService.takeHistory();
    }

    @Override
    public void save() {
        LibraryService libraryService = LibraryWebWorker.takeLibraryService();
        libraryService.requestSerializeData();
    }
}
