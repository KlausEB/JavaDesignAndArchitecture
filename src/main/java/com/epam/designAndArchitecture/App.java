package com.epam.designAndArchitecture;

import com.epam.designAndArchitecture.entities.Author;
import com.epam.designAndArchitecture.userInterface.ConsoleUserInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nd4j.shade.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class App {
    public final static Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws IOException {
        IUserInterface userInterface = new ConsoleUserInterface();
        try {
            userInterface.startWorking();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        ObjectMapper mapper = new ObjectMapper();
//        File file = new File("DB/sex.json");
//        SerializableObject[] serializableObject = {new Author("Dbn"), new Author("fds")};
//        mapper.writeValue(file, serializableObject);
//        SerializableObject[] sss = mapper.readValue(file, Author[].class);
    }
}
