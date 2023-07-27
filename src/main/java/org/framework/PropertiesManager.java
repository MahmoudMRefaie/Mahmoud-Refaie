package org.framework;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {

    private final String projectPropertiesFilePath = "src/main/resources/properties/project.properties";

    public PropertiesManager(){
        readProperties(projectPropertiesFilePath);
    }

    /**
     * This method is ued to read properties in properties file and load into system properties
     * @param propertiesFilePath properties file path
     * @return all properties
     */
    private void readProperties(String propertiesFilePath) {
        FileReader reader = null;
        Properties prop = null;
        try {
            reader = new FileReader(propertiesFilePath);
            prop = new Properties();
            prop.load(reader);
        } catch (FileNotFoundException e) {
            System.out.println("Can't read the file");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Can't read this property");
            throw new RuntimeException(e);
        }
        //load properties into system properties
        System.getProperties().putAll(prop);
    }

}
