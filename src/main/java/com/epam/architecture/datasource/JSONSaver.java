package com.epam.architecture.datasource;

import com.epam.architecture.App;
import com.epam.architecture.SavableObject;
import com.epam.architecture.exceptions.SaveInDataSourceException;
import org.apache.logging.log4j.Logger;
import org.nd4j.shade.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONSaver {
    public static final Logger logger = App.logger;
    private ObjectMapper mapper = new ObjectMapper();
    private String pathToSave;

    public JSONSaver(String pathToSave) {
        this.pathToSave = pathToSave;
    }

    public void saveObjects(SavableObject[] potentialJSON) throws IOException {
        File fileToSave = new File(pathToSave);
        if (!fileToSave.exists() && !fileToSave.createNewFile()) {
            SaveInDataSourceException exception = new SaveInDataSourceException();
            logger.error("File is not exist", exception);
            throw exception;
        }
        mapper.writeValue(fileToSave, potentialJSON);
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public String getPathToSave() {
        return pathToSave;
    }

    public void setPathToSave(String pathToSave) {
        this.pathToSave = pathToSave;
    }
}
