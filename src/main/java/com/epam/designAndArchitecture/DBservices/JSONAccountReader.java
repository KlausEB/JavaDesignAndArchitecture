package com.epam.designAndArchitecture.DBservices;

import com.epam.designAndArchitecture.SerializableObject;
import com.epam.designAndArchitecture.entities.User;
import com.epam.designAndArchitecture.exceptions.DeserializationException;
import org.nd4j.shade.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONAccountReader extends JSONReader{

    public JSONAccountReader(String pathForRead) {
        super(pathForRead);
    }

    @Override
    public SerializableObject[] loadObjects() throws IOException {
        File fileToRead = new File(super.pathForRead);
        if (!fileToRead.exists()) {
            throw new DeserializationException();
        }
        return mapper.readValue(fileToRead, User[].class);
    }
}
