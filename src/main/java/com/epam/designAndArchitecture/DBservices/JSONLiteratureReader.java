package com.epam.designAndArchitecture.DBservices;

import com.epam.designAndArchitecture.SerializableObject;
import com.epam.designAndArchitecture.entities.Author;
import com.epam.designAndArchitecture.entities.User;
import com.epam.designAndArchitecture.exceptions.DeserializationException;

import java.io.File;
import java.io.IOException;

public class JSONLiteratureReader extends JSONReader{
    public JSONLiteratureReader(String pathForRead) {
        super(pathForRead);
    }

    @Override
    public SerializableObject[] loadObjects() throws IOException {
        File fileToRead = new File(super.pathForRead);
        if (!fileToRead.exists()) {
            throw new DeserializationException();
        }
        return mapper.readValue(fileToRead, Author[].class);
    }
}
