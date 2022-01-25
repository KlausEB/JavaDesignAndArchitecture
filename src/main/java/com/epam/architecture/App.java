package com.epam.architecture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class App {
    public static final Logger logger = LogManager.getLogger();
    public static final Properties properties = new Properties();
    public static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static void main(String[] args) {
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream("src/main/resources/source.properties"))) {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
