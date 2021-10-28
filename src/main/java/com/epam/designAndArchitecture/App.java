package com.epam.designAndArchitecture;

import com.epam.designAndArchitecture.userInterface.ConsoleUserInterface;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        IUserInterface userInterface = new ConsoleUserInterface();
        try {
            userInterface.startWorking();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
