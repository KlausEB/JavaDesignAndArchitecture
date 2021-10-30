package com.epam.designAndArchitecture.account;

import com.epam.designAndArchitecture.DBservices.DBService;
import com.epam.designAndArchitecture.DBservices.JSONDatabase;
import com.epam.designAndArchitecture.entities.User;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AccountDBService {
    private final DBService dbService;
    public String pathToJSONFile;

    public AccountDBService(String pathToJSONFile) {
        this.pathToJSONFile = pathToJSONFile;
        dbService = new JSONDatabase(pathToJSONFile);
    }

    public void saveAccountsData(Map<String, User> takeAuthorsData) throws IOException {
        Collection<User> users = takeAuthorsData.values();
        User[] authorsArray = (User[]) users.toArray();
        dbService.saveData(authorsArray);
    }

    public Map<String, User> takeAccountsData() throws IOException {
        Map<String, User> usersMap = new HashMap<>();
        User[] usersFromDB = (User[]) dbService.restoreData();
        for (User currentUser : usersFromDB) {
            usersMap.put(currentUser.getLogin(), currentUser);
        }
        return usersMap;
    }
}
