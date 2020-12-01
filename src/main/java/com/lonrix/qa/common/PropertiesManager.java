package com.lonrix.qa.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * <h1>PropertiesManager Class</h1>
 * This class is used to read properties file
 * <p>
 * @author Jaspal Aujla
 */
public class PropertiesManager {

    //********** LOGGER OBJECT DECLARATION/INITIALIZATION **********
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesManager.class);

    //********** OBJECT DECLARATION **********
    private Properties properties;
    private FileInputStream fileInputStream;
    private final String filePath;

    /**
     * Parameterized Constructor
     *
     * @param filePath
     */
    public PropertiesManager(String filePath) {
        LOGGER.info("Constructing PropertiesManager with parameter: " + filePath);
        this.filePath = filePath;
        loadPropertiesFile();
    }

    /**
     * Get property as String
     *
     * @param key
     * @return String
     */
    public String getProperty(String key) {
        LOGGER.info("Returning Property of (" + key + ") as String");
        return properties.getProperty(key);
    }

    /**
     * Get property as boolean
     *
     * @param key
     * @return boolean
     */
    public boolean getPropertyAsBoolean(String key) {
        LOGGER.info("Returning Property of (" + key + ") as boolean");
        return Boolean.parseBoolean(properties.getProperty(key));
    }

    /**
     * Get property as int
     *
     * @param key
     * @return int
     */
    public int getPropertyAsInt(String key) {
        LOGGER.info("Returning Property of (" + key + ") as int");
        try {
            return Integer.parseInt(properties.getProperty(key));
        } catch (Throwable throwable) {
            LOGGER.error("Property key (" + key + ") conversion to Int failed. Exception occurred: " + throwable);
            throw throwable;
        }

    }

    /**
     * Get property as long
     *
     * @param key
     * @return long
     */
    public long getPropertyAsLong(String key) {
        LOGGER.info("Returning Property of (" + key + ") as long");
        try {
            return Long.parseLong(properties.getProperty(key));
        } catch (Throwable throwable) {
            LOGGER.error("Property key (" + key + ") conversion to Long failed. Exception occurred: " + throwable);
            throw throwable;
        }
    }

    /**
     * Load properties file
     */
    private void loadPropertiesFile() {
        LOGGER.info("Loading properties file");
        try {
            properties = new Properties();
            if(fileInputStream == null) {
                fileInputStream = new FileInputStream(filePath);
            }
            properties.load(fileInputStream);
            LOGGER.info("Properties File (" + filePath + ") loaded successfully");
        }
        catch (IOException io) {
            LOGGER.error("Load Properties File (" + filePath + ") failed. Exception occurred: " + io);
        }
        finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                }
                catch (IOException io) {
                    LOGGER.error("Close Properties File (" + filePath + ") failed. Exception occurred: " + io);
                }
            }
        }
    }

}
