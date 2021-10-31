package com.epam.designAndArchitecture.DBservices;

import com.epam.designAndArchitecture.SerializableObject;
import org.nd4j.shade.jackson.databind.ObjectMapper;

import java.io.IOException;

public abstract class JSONReader {
    protected final ObjectMapper mapper = new ObjectMapper();
    protected String pathForRead;

    public JSONReader(String pathForRead) {
        this.pathForRead = pathForRead;
    }

    public abstract SerializableObject[] loadObjects() throws IOException;

    public String getPathForRead() {
        return pathForRead;
    }

    public void setPathForRead(String pathForRead) {
        this.pathForRead = pathForRead;
    }
}
