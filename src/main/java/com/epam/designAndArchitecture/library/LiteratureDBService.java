package com.epam.designAndArchitecture.library;

import com.epam.designAndArchitecture.DBservices.*;
import com.epam.designAndArchitecture.entities.Author;
import com.epam.designAndArchitecture.exceptions.DeserializationException;
import com.epam.designAndArchitecture.exceptions.SerializationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LiteratureDBService {
    public static final Logger logger = LogManager.getLogger();
    private final JSONSaver dbSaver;
    private final JSONReader dbReader;
    public final String pathToJSONFile;

    public LiteratureDBService(String pathToJSONFile) {
        this.pathToJSONFile = pathToJSONFile;
        this.dbSaver = new JSONSaver(pathToJSONFile);
        this.dbReader = new JSONLiteratureReader(pathToJSONFile);
    }

    public void saveLiteratureData(List<Author> takeAuthorsData) {
        Author[] authors = takeAuthorsData.toArray(new Author[0]);
        try {
            dbSaver.saveObjects(authors);
        } catch (IOException e) {
            SerializationException exception = new SerializationException(e);
            logger.error("Failed to save literature data", exception);
            throw exception;
        }
    }

    public List<Author> takeLiteratureData() {
        try {
            return new ArrayList<Author>(Arrays.asList((Author[]) dbReader.loadObjects()));
        } catch (IOException e) {
            DeserializationException exception = new DeserializationException(e);
            logger.error("Failed to take literature data", exception);
            throw exception;
        }
    }
}
