package com.epam.designAndArchitecture.dataSourceServices;

import com.epam.designAndArchitecture.App;
import com.epam.designAndArchitecture.SavableObject;
import com.epam.designAndArchitecture.exceptions.SaveInDataSourceException;
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
        if (!fileToSave.exists()) {
            if (fileToSave.createNewFile()) {
                SaveInDataSourceException exception = new SaveInDataSourceException();
                logger.error("File is not exist", exception);
                throw exception;
            }
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

    public String getFileForSave() {
        return pathToSave;
    }

    public void setFileForSave(String pathToSave) {
        this.pathToSave = pathToSave;
    }
}
