package org.framework;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

public class JSONFileManager {
    private String jsonFilePath;
    private FileReader reader = null;

    public JSONFileManager(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
        initializeReader();
    }

    /**
     * This method is ued to read data in json file as string
     * @param jsonPath data path in the json file
     * @return string value
     */
    public String getTestData(String jsonPath) {
        Object testData = getJsonTestData(jsonPath);
        return testData != null ? String.valueOf(testData) : null;
    }

    /**
     * This method is ued to read data in json file as list
     * @param jsonPath data path in the json file
     * @return value as list
     */
    public List<?> getTestDataAsList(String jsonPath){
        Object testData = this.getJsonTestData(jsonPath);
        return testData != null ? (List)List.class.cast(testData) : null;
    }

    /**
     * This method is ued to read data in json file as list of objects
     * @param jsonPath data path in the json file
     * @return value as list of objects
     */
    public Object[] getTestDataAsArrayObjects(String jsonPath){
        List<?> list = getTestDataAsList(jsonPath);
        return list.toArray(new Object[list.size()]);
    }

    /**
     * This method is used to read data from json file
     * @param jsonPath data path in the json file
     * @return value as object
     */
    private Object getJsonTestData(String jsonPath) {
        Object testData = null;
        initializeReader();

        try {
            JsonObject jsonObject = new JsonParser().parse(reader).getAsJsonObject();
            testData = JsonPath.read(String.valueOf(jsonObject), jsonPath);
        } catch (PathNotFoundException ex) {
            System.out.println("Couldn't find the desired path [" + jsonPath + "]");
            throw new PathNotFoundException(ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return testData;
    }

    /**
     * This method is used to initialize reader to use json file
     */
    private void initializeReader() {
        reader = null;

        try {
            reader = new FileReader(jsonFilePath);
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find the desired file [" + jsonFilePath + "]");
            throw new RuntimeException(e);
        }
    }
}
