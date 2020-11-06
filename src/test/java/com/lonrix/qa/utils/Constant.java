package com.lonrix.qa.utils;

/**
 * <h1>Directory Paths</h1>
 * This class is used to keep the path of all directories at a single place.
 * <p>
 * @author Jaspal
 */
public class Constant {

    public static final String CONFIG_QA = "config-qa";
    public static final String CONFIG_DEV = "config-dev";
    public static final String CONFIG_UAT = "config-uat";
    public static final String CONFIG_PROD = "config-prod";

    public static final String QA_CONFIG_PROPERTIES_DIRECTORY = System.getProperty("user.dir") + "/src/test/resources/config-qa.properties";
    public static final String DEV_CONFIG_PROPERTIES_DIRECTORY = System.getProperty("user.dir") + "/src/test/resources/config-dev.properties";
    public static final String UAT_CONFIG_PROPERTIES_DIRECTORY = System.getProperty("user.dir") + "/src/test/resources/config-uat.properties";
    public static final String PROD_CONFIG_PROPERTIES_DIRECTORY = System.getProperty("user.dir") + "/src/test/resources/config-prod.properties";

}
