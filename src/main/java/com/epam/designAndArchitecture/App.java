package com.epam.designAndArchitecture;

import com.epam.designAndArchitecture.userInterface.ConsoleUserInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class App {
    public final static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        IUserInterface userInterface = new ConsoleUserInterface();
        try {
            userInterface.startWorking();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
