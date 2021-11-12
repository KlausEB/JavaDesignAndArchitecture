package com.epam.architecture;

import com.epam.architecture.userinterface.ConsoleUserInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class App {
    public static final Logger logger = LogManager.getLogger();
    public static final Properties properties = new Properties();

    public static void main(String[] args) {
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream("src/main/resources/source.properties"))) {
            properties.load(reader);
            IUserInterface userInterface = new ConsoleUserInterface();
            userInterface.startWorking();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
