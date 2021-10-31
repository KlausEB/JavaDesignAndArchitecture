package com.epam.designAndArchitecture.DBservices;

import com.epam.designAndArchitecture.SerializableObject;
import com.epam.designAndArchitecture.exceptions.SerializationException;
import org.nd4j.shade.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONSaver {
    private ObjectMapper mapper = new ObjectMapper();
    private String pathToSave;

    public JSONSaver(String pathToSave) {
        this.pathToSave = pathToSave;
    }

    public void saveObjects(SerializableObject[] potentialJSON) throws IOException {
        File fileToSave = new File(pathToSave);
        if (!fileToSave.exists()) {
            if (fileToSave.createNewFile()) {
                throw new SerializationException();
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
