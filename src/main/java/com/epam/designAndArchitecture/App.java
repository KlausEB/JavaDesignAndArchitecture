package com.epam.designAndArchitecture;

import com.epam.designAndArchitecture.entities.Author;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nd4j.shade.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class App {
    public final static Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws IOException {
//        IUserInterface userInterface = new ConsoleUserInterface();
//        try {
//            userInterface.startWorking();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Author author = new Author("Вася");
        author.appendBook("name", 10, 10, "sfdsgfd");
        Author authorTwo = new Author("Дима");
        author.appendBook("name", 11, 10, "sfdsgfd");
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("DB/Author.json");
        mapper.writeValue(file, author);
        Author newAuthor = mapper.readValue(file, Author.class);
        System.out.println(newAuthor.takeBooks());
    }
}
