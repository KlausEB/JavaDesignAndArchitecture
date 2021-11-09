package com.epam.designAndArchitecture.dataSourceServices;

import com.epam.designAndArchitecture.App;
import com.epam.designAndArchitecture.SavableObject;
import com.epam.designAndArchitecture.entities.Author;
import com.epam.designAndArchitecture.entities.Book;
import com.epam.designAndArchitecture.entities.Bookmark;
import com.epam.designAndArchitecture.entities.User;
import com.epam.designAndArchitecture.exceptions.RestoreFromDataSourceException;
import org.apache.logging.log4j.Logger;
import org.nd4j.shade.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONReader {
    public static final Logger logger = App.logger;
    protected final ObjectMapper mapper = new ObjectMapper();
    protected String pathForRead;

    public JSONReader(String pathForRead) {
        this.pathForRead = pathForRead;
    }

    public SavableObject[] loadObjects(DataSourceType type) throws IOException {
        File fileToRead = new File(pathForRead);
        if (!fileToRead.exists()) {
            throw new RestoreFromDataSourceException();
        }
        switch (type) {
            case ACCOUNT:
                return mapper.readValue(fileToRead, User[].class);
            case AUTHOR:
                return mapper.readValue(fileToRead, Author[].class);
            case BOOK:
                return mapper.readValue(fileToRead, Book[].class);
            case BOOKMARK:
                return mapper.readValue(fileToRead, Bookmark[].class);
            default:
                RestoreFromDataSourceException exception = new RestoreFromDataSourceException();
                logger.error("Failed to take data", exception);
                throw exception;
        }
    }

    public String getPathForRead() {
        return pathForRead;
    }

    public void setPathForRead(String pathForRead) {
        this.pathForRead = pathForRead;
    }
}
