package com.lonrix.qa.steps;

import com.lonrix.qa.common.PropertiesManager;
import com.lonrix.qa.common.WebDriverManager;
import com.lonrix.qa.constant.Constant;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * <h1>Test Common Steps</h1>
 * This class is used to manage common steps of test scenarios, which need to be performed before and after each test.
 * <p>
 * @author Jaspal Aujla
 */
public class Hooks {

    //********** LOGGER OBJECT DECLARATION/INITIALIZATION **********
    private static final Logger LOGGER = LoggerFactory.getLogger(Hooks.class);

    //********** OBJECT DECLARATION **********
    private final DependencyContainer dependencyContainer;

    /**
     * Parameterized Constructor
     *
     * @param dependencyContainer
     */
    public Hooks(DependencyContainer dependencyContainer) {
        this.dependencyContainer = dependencyContainer;
    }

    @Before
    public void setUp(Scenario scenario) {
        LOGGER.info("XXXXXXXXXX" + " START TEST SCENARIO " + "XXXXXXXXXX");
        LOGGER.info("Scenario: " + scenario.getName());

        dependencyContainer.webDriverManager = new WebDriverManager(setConfig());
        maximizeWindow();
        setImplicitlyWait();
        setPageLoadTimeout();
    }

    @After()
    public void tearDown(Scenario scenario) {
        quitWebDriver();
        LOGGER.info("XXXXXXXXXX" + " END TEST SCENARIO " + "XXXXXXXXXX");
    }

    /**
     * Read config properties file based on the parameter provided by command-line execution.
     */
    private PropertiesManager setConfig() {
        String environmentType = System.getProperty("config.file", Constant.CONFIG_PROD);
        String configFilePath = null;
        switch (environmentType) {
            case Constant.CONFIG_DEV:
                configFilePath = Constant.DEV_CONFIG_PROPERTIES_DIRECTORY;
                break;
            case Constant.CONFIG_QA:
                configFilePath = Constant.QA_CONFIG_PROPERTIES_DIRECTORY;
                break;
            case Constant.CONFIG_UAT:
                configFilePath = Constant.UAT_CONFIG_PROPERTIES_DIRECTORY;
                break;
            default:
                configFilePath = Constant.PROD_CONFIG_PROPERTIES_DIRECTORY;
        }
        return dependencyContainer.propertiesManager = new PropertiesManager(configFilePath);
    }

    /**
     * Maximize web browser window.
     */
    private void maximizeWindow() {
        if(dependencyContainer.propertiesManager.getPropertyAsBoolean("windows.maximize")) {
            dependencyContainer.webDriverManager.getDriver().manage().window().maximize();
            LOGGER.info("Browser windows maximized successfully");
        }
    }

    /**
     * Set implicitly wait.
     */
    private void setImplicitlyWait() {
        if(dependencyContainer.propertiesManager.getPropertyAsLong("implicitly.wait") != 0) {
            dependencyContainer.webDriverManager.getDriver().manage().timeouts().implicitlyWait(dependencyContainer.propertiesManager.getPropertyAsLong("implicitly.wait"), TimeUnit.SECONDS);
            LOGGER.info("ImplicitlyWait ["+ dependencyContainer.propertiesManager.getPropertyAsLong("implicitly.wait") +" second(s)] implemented successfully");
        } else {
            LOGGER.info("ImplicitlyWait not implement");
        }
    }

    private void setPageLoadTimeout() {
        if(dependencyContainer.propertiesManager.getPropertyAsLong("page.load.timeout") != 0) {
            dependencyContainer.webDriverManager.getDriver().manage().timeouts().pageLoadTimeout(dependencyContainer.propertiesManager.getPropertyAsLong("page.load.timeout"), TimeUnit.SECONDS);
            LOGGER.info("Page load timeout ["+ dependencyContainer.propertiesManager.getPropertyAsLong("page.load.timeout") +" second(s)] implemented successfully");
        } else {
            LOGGER.info("Page load timeout not implement");
        }
    }

    /**
     * Quit web browser window(s) and WebDriver session.
     */
    private void quitWebDriver() {
        if (dependencyContainer.webDriverManager.getDriver() != null) {
            dependencyContainer.webDriverManager.getDriver().quit();
            LOGGER.info("Web browser quit successfully");
        }
    }

}
