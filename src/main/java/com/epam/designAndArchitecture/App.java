package com.epam.designAndArchitecture;

import com.epam.designAndArchitecture.userInterface.ConsoleUserInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class App {
    public final static Logger logger = LogManager.getLogger();
    public static Properties properties = new Properties();

    public static void main(String[] args) {
        try {
            properties.load(new InputStreamReader(new FileInputStream("src/main/resources/source.properties")));
            IUserInterface userInterface = new ConsoleUserInterface();
            userInterface.startWorking();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
