package com.epam.designAndArchitecture.DBservices;

import com.epam.designAndArchitecture.SerializableObject;
import com.epam.designAndArchitecture.exceptions.DeserializationException;
import org.nd4j.shade.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONReader {
    private final ObjectMapper mapper;
    private String fileForRead;

    public JSONReader(String fileForRead, ObjectMapper mapper) {
        this.fileForRead = fileForRead;
        this.mapper = mapper;
    }

    public SerializableObject[] readObjects() throws IOException {
        File fileToSave = new File(fileForRead);
        if (!fileToSave.exists()) {
            throw new DeserializationException();
        }
        return mapper.readValue(fileForRead, SerializableObject[].class);
    }

    public String getFileForRead() {
        return fileForRead;
    }

    public void setFileForRead(String fileForRead) {
        this.fileForRead = fileForRead;
    }
}
