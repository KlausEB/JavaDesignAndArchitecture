package com.epam.architecture.datasource;

import com.epam.architecture.exceptions.RestoreFromDataSourceException;
import com.epam.architecture.exceptions.SaveInDataSourceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class JSONDataSourceService<T extends Serializable> implements LibraryDAO<T> {
    public static final Logger logger = LogManager.getLogger();
    public final String pathToJSONFile;
    private final EntityTypes sourceType;
    private final JSONSaver<T> dbSaver;
    private final JSONReader<T> dbReader;

    public JSONDataSourceService(String pathToJSONFile, EntityTypes sourceType) {
        this.pathToJSONFile = pathToJSONFile;
        this.sourceType = sourceType;
        this.dbSaver = new JSONSaver<>(pathToJSONFile);
        this.dbReader = new JSONReader<>(pathToJSONFile);
    }

    @SafeVarargs
    @Override
    public final void saveData(T... accountsData) {
        try {
            dbSaver.saveObjects(accountsData);
        } catch (IOException e) {
            SaveInDataSourceException exception = new SaveInDataSourceException(e);
            logger.error("Failed to save account data", exception);
            throw exception;
        }
    }

    @Override
    public void deleteData(T... data) {
        // I'm so lazy... rly
    }

    @Override
    public List<T> restoreData() {
        try {
            return dbReader.loadObjects(sourceType);
        } catch (IOException e) {
            RestoreFromDataSourceException exception = new RestoreFromDataSourceException(e);
            logger.error("Failed to take account data", exception);
            throw exception;
        }
    }
}
