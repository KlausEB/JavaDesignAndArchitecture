package com.epam.designAndArchitecture.dataSourceServices;

import com.epam.designAndArchitecture.SavableObject;
import com.epam.designAndArchitecture.exceptions.RestoreFromDataSourceException;
import com.epam.designAndArchitecture.exceptions.SaveInDataSourceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class DataSourceService {
    public static final Logger logger = LogManager.getLogger();
    public final String pathToJSONFile;
    private final DataSourceType sourceType;
    private final JSONSaver dbSaver;
    private final JSONReader dbReader;

    public DataSourceService(String pathToJSONFile, DataSourceType sourceType) {
        this.pathToJSONFile = pathToJSONFile;
        this.sourceType = sourceType;
        this.dbSaver = new JSONSaver(pathToJSONFile);
        this.dbReader = new JSONReader(pathToJSONFile);
    }

    public void saveData(SavableObject[] accountsData) {
        try {
            dbSaver.saveObjects(accountsData);
        } catch (IOException e) {
            SaveInDataSourceException exception = new SaveInDataSourceException(e);
            logger.error("Failed to save account data", exception);
            throw exception;
        }
    }

    public SavableObject[] restoreData() {
        try {
            return dbReader.loadObjects(sourceType);
        } catch (IOException e) {
            RestoreFromDataSourceException exception = new RestoreFromDataSourceException(e);
            logger.error("Failed to take account data", exception);
            throw exception;
        }
    }
}
