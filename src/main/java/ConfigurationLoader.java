package com.framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationLoader {

    private Properties properties;

    public ConfigurationLoader() {
        properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration properties");
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
