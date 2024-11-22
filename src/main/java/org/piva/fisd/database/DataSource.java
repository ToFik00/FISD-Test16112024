package org.piva.fisd.database;

import org.piva.fisd.util.PropertiesReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataSource {

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        PropertiesReader propertiesReader = new PropertiesReader();
        return DriverManager.getConnection(propertiesReader.getProperty("url"),
                propertiesReader.getProperty("username"),
                propertiesReader.getProperty("password"));
    }

}
