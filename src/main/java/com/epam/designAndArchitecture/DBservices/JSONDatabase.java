package com.epam.designAndArchitecture.DBservices;

import com.epam.designAndArchitecture.SerializableObject;
import org.nd4j.shade.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JSONDatabase implements DBService {
    private final ObjectMapper mapper = new ObjectMapper();
    private String pathToDBDir;
    private final JSONSaver saver = new JSONSaver(pathToDBDir, mapper);
    private final JSONReader reader = new JSONReader(pathToDBDir, mapper);

    public JSONDatabase(String pathToJSONDB) {
        this.pathToDBDir = pathToJSONDB;
    }

    @Override
    public void saveData(SerializableObject[] potentialJSON) throws IOException {
        saver.saveObjects(potentialJSON);
    }

    @Override
    public SerializableObject[] restoreData() throws IOException {
        return reader.readObjects();
    }

    public String getPathToDBDir() {
        return pathToDBDir;
    }

    public void setPathToDBDir(String pathToDBDir) {
        this.pathToDBDir = pathToDBDir;
    }
}
