package com.epam.designAndArchitecture.library;

import com.epam.designAndArchitecture.DBservices.DBService;
import com.epam.designAndArchitecture.DBservices.JSONDatabase;
import com.epam.designAndArchitecture.entities.Author;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LiteratureDBService {
    private final DBService dbService;
    public String pathToJSONFile;

    public LiteratureDBService(String pathToJSONFile) {
        this.pathToJSONFile = pathToJSONFile;
        this.dbService = new JSONDatabase(pathToJSONFile);
    }

    public void saveLiteratureData(List<Author> takeAuthorsData) throws IOException {
        Author[] authors = (Author[]) takeAuthorsData.toArray();
        dbService.saveData(authors);
    }

    public List<Author> takeLiteratureData() throws IOException {
        return new ArrayList<Author>(Arrays.asList((Author[]) dbService.restoreData()));
    }
}
