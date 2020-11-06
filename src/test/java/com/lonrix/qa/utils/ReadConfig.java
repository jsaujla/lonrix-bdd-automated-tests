package com.lonrix.qa.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * <h1>Read Properties File</h1>
 * This class is used to read properties (configuration) file.
 * <p>
 * @author Jaspal
 */
public class ReadConfig {

    //********** OBJECT DECLARATION **********
    private Properties properties;
    private FileInputStream fileInputStream;
    private String filePath;

    //********** PARAMETERIZED CONSTRUCTOR **********
    public ReadConfig(String filePath) {
        this.filePath = filePath;
        loadPropertiesFile();
    }


    //********** CLASS METHODS **********

    public String getProjectName() {
        return properties.getProperty("project.name");
    }

    public String getProjectType() {
        return properties.getProperty("project.type");
    }

    public String getWebBrowser() {
        return properties.getProperty("web.browser");
    }

    public String getJunoViewerBaseUrl() {
        return properties.getProperty("juno.viewer.base.url");
    }

    public long getImplicitlyWait() {
        return Long.parseLong(properties.getProperty("implicitly.wait"));
    }

    public long getWebDriverWait() {
        return Long.parseLong(properties.getProperty("web.driver.wait"));
    }

    public boolean isWindowsMaximize() {
        return Boolean.parseBoolean(properties.getProperty("windows.maximize"));
    }

    public String getPropertyKey(String propertyKey) {
        return properties.getProperty(propertyKey);
    }

    private Properties loadPropertiesFile() {
        try {
            properties = new Properties();
            if(fileInputStream == null) {
                fileInputStream = new FileInputStream(filePath);
            }
            properties.load(fileInputStream);
            return properties;
        }
        catch (IOException io) {
            io.printStackTrace();
            return null;
        }
        finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                }
                catch (IOException io) {
                    io.printStackTrace();
                }
            }
        }
    }

}
