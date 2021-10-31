package com.epam.designAndArchitecture.account;

import com.epam.designAndArchitecture.DBservices.JSONAccountReader;
import com.epam.designAndArchitecture.DBservices.JSONReader;
import com.epam.designAndArchitecture.DBservices.JSONSaver;
import com.epam.designAndArchitecture.entities.User;
import com.epam.designAndArchitecture.exceptions.DeserializationException;
import com.epam.designAndArchitecture.exceptions.SerializationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AccountDBService {
    public static final Logger logger = LogManager.getLogger();
    private final JSONSaver dbSaver;
    private final JSONReader dbReader;
    public final String pathToJSONFile;

    public AccountDBService(String pathToJSONFile) {
        this.pathToJSONFile = pathToJSONFile;
        this.dbSaver = new JSONSaver(pathToJSONFile);
        this.dbReader = new JSONAccountReader(pathToJSONFile);
    }

    public void saveAccountsData(Map<String, User> takeAuthorsData) {
        Collection<User> users = takeAuthorsData.values();
        User[] authorsArray = users.toArray(new User[0]);
        try {
            dbSaver.saveObjects(authorsArray);
        } catch (IOException e) {
            SerializationException exception = new SerializationException(e);
            logger.error("Failed to save account data", exception);
            throw exception;
        }
    }

    public Map<String, User> takeAccountsData() {
        Map<String, User> usersMap = new HashMap<>();
        User[] usersFromDB;
        try {
            usersFromDB = (User[]) dbReader.loadObjects();
        } catch (IOException e) {
            DeserializationException exception = new DeserializationException(e);
            logger.error("Failed to take account data", exception);
            throw exception;
        }
        for (User currentUser : usersFromDB) {
            usersMap.put(currentUser.getLogin(), currentUser);
        }
        return usersMap;
    }
}
