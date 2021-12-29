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
import java.util.List;

public class JSONReader<T extends Serializable> {
    public static final Logger logger = App.logger;
    protected final ObjectMapper mapper = new ObjectMapper();
    protected String pathForRead;

    public JSONReader(String pathForRead) {
        this.pathForRead = pathForRead;
    }

    public List<T> loadObjects(EntityTypes type) throws IOException {
        File fileToRead = new File(pathForRead);
        if (!fileToRead.exists()) {
            throw new RestoreFromDataSourceException();
        }
        return mapper.readValue(fileToRead, mapper.getTypeFactory().constructCollectionType(List.class, type.getType()));
    }

    public String getPathForRead() {
        return pathForRead;
    }

    public void setPathForRead(String pathForRead) {
        this.pathForRead = pathForRead;
    }
}
