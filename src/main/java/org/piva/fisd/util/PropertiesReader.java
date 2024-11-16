package org.piva.fisd.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader{
    Properties properties;

    public PropertiesReader() {

        try (FileReader reader = new FileReader(ClassLoader.getSystemResource("config.properties").getPath())) {
            properties = new Properties();
            properties.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
