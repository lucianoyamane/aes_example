package br.com.example.properties;

import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {

    private final Properties properties;

    ApplicationProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    public String readProperty(String keyName) {
        return properties.getProperty(keyName, "There is no key in the properties file");
    }

}
