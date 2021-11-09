package com.epam.architecture.datasource;

import com.epam.architecture.App;
import com.epam.architecture.entities.Author;
import com.epam.architecture.entities.Book;
import com.epam.architecture.entities.Bookmark;
import com.epam.architecture.entities.User;
import com.epam.architecture.exceptions.RestoreFromDataSourceException;
import org.apache.logging.log4j.Logger;
import org.nd4j.shade.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class JSONReader<T extends Serializable> {
    public static final Logger logger = App.logger;
    protected final ObjectMapper mapper = new ObjectMapper();
    protected String pathForRead;

    public JSONReader(String pathForRead) {
        this.pathForRead = pathForRead;
    }

    public T[] loadObjects(DataSourceType type) throws IOException {
        File fileToRead = new File(pathForRead);
        if (!fileToRead.exists()) {
            throw new RestoreFromDataSourceException();
        }
        Serializable[] deserializableObjects;
        switch (type) {
            case ACCOUNT:
                deserializableObjects = mapper.readValue(fileToRead, User[].class);
                break;
            case AUTHOR:
                deserializableObjects = mapper.readValue(fileToRead, Author[].class);
                break;
            case BOOK:
                deserializableObjects = mapper.readValue(fileToRead, Book[].class);
                break;
            case BOOKMARK:
                deserializableObjects = mapper.readValue(fileToRead, Bookmark[].class);
                break;
            default:
                RestoreFromDataSourceException exception = new RestoreFromDataSourceException();
                logger.error("Failed to take data", exception);
                throw exception;
        }
        return (T[]) deserializableObjects;
    }

    public String getPathForRead() {
        return pathForRead;
    }

    public void setPathForRead(String pathForRead) {
        this.pathForRead = pathForRead;
    }
}
