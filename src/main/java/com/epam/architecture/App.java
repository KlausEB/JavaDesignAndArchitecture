package com.epam.architecture;

import com.epam.architecture.userinterface.ConsoleUserInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

public class App {
    public static final Logger logger = LogManager.getLogger();
    public static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static void main(String[] args) {
        try {
            IUserInterface userInterface = new ConsoleUserInterface();
            userInterface.startWorking();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
