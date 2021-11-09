package com.epam.architecture.datasource;

import com.epam.architecture.exceptions.RestoreFromDataSourceException;
import com.epam.architecture.exceptions.SaveInDataSourceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Serializable;

public class DataSourceService<T extends Serializable> {
    public static final Logger logger = LogManager.getLogger();
    public final String pathToJSONFile;
    private final DataSourceType sourceType;
    private final JSONSaver dbSaver;
    private final JSONReader<T> dbReader;

    public DataSourceService(String pathToJSONFile, DataSourceType sourceType) {
        this.pathToJSONFile = pathToJSONFile;
        this.sourceType = sourceType;
        this.dbSaver = new JSONSaver(pathToJSONFile);
        this.dbReader = new JSONReader<>(pathToJSONFile);
    }

    public void saveData(Serializable[] accountsData) {
        try {
            dbSaver.saveObjects(accountsData);
        } catch (IOException e) {
            SaveInDataSourceException exception = new SaveInDataSourceException(e);
            logger.error("Failed to save account data", exception);
            throw exception;
        }
    }

    public T[] restoreData() {
        try {
            return dbReader.loadObjects(sourceType);
        } catch (IOException e) {
            RestoreFromDataSourceException exception = new RestoreFromDataSourceException(e);
            logger.error("Failed to take account data", exception);
            throw exception;
        }
    }
}
