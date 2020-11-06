package com.lonrix.qa.steps;

import com.lonrix.qa.utils.Constant;
import com.lonrix.qa.utils.ReadConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * <h1>Test Common Steps</h1>
 * This class is used to manage common steps of test scenarios, which need to be performed before and after each test.
 * <p>
 * @author Jaspal
 */
public class Hooks {

    //********** LOGGER OBJECT DECLARATION/INITIALIZATION **********
    private static final Logger LOGGER = LoggerFactory.getLogger(Hooks.class);

    //********** OBJECT DECLARATION **********
    private DependencyContainer dependencyContainer;

    //********** CONSTRUCTOR **********
    public Hooks(DependencyContainer dependencyContainer) {
        this.dependencyContainer = dependencyContainer;
    }

    @Before
    public void setUp(Scenario scenario) {
        LOGGER.info("XXXXXXXXXX" + " START TEST SCENARIO " + "XXXXXXXXXX");
        LOGGER.info("Scenario: " + scenario.getName());

        setConfig();
        initializeWebDriver();
        maximizeWindow();
        setImplicitlyWait();
    }

    @After()
    public void tearDown(Scenario scenario) {
        quitWebDriver();
        LOGGER.info("XXXXXXXXXX" + " END TEST SCENARIO " + "XXXXXXXXXX");
    }

    /**
     * Read config properties file based on the parameter provided by command-line execution.
     */
    private void setConfig() {
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
        dependencyContainer.configFile = new ReadConfig(configFilePath);
    }

    /**
     * Launch/start web browser window and WebDriver session
     */
    private void initializeWebDriver() {
        if(dependencyContainer.configFile.getWebBrowser().toLowerCase().contains("chrome")) {
            WebDriverManager.chromedriver().setup();
            dependencyContainer.driver = new ChromeDriver();
        } else if(dependencyContainer.configFile.getWebBrowser().toLowerCase().contains("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            dependencyContainer.driver = new FirefoxDriver();
        } else if(dependencyContainer.configFile.getWebBrowser().toLowerCase().contains("ie")
                || dependencyContainer.configFile.getWebBrowser().toLowerCase().contains("internet explorer")) {
            WebDriverManager.iedriver().setup();
            dependencyContainer.driver = new InternetExplorerDriver();
        } else if(dependencyContainer.configFile.getWebBrowser().toLowerCase().contains("edge")) {
            WebDriverManager.edgedriver().setup();
            dependencyContainer.driver = new EdgeDriver();
        } else if(dependencyContainer.configFile.getWebBrowser().toLowerCase().contains("opera")) {
            WebDriverManager.operadriver().setup();
            dependencyContainer.driver = new OperaDriver();
        } else {
            LOGGER.error("Value of 'web.browser' in 'config properties' file should be: chrome, firefox, ie, edge or opera");
            throw new IllegalArgumentException("Value of 'web.browser' in 'config properties' file should be: chrome, firefox, ie, edge or opera");
        }
        LOGGER.info("Web browser (" + dependencyContainer.configFile.getWebBrowser() + ") launched successfully");
    }

    /**
     * Maximize web browser window.
     */
    private void maximizeWindow() {
        if(dependencyContainer.configFile.isWindowsMaximize()) {
            dependencyContainer.driver.manage().window().maximize();
            LOGGER.info("Browser windows maximized successfully");
        }
    }

    /**
     * Set implicitly wait.
     */
    private void setImplicitlyWait() {
        if(dependencyContainer.configFile.getImplicitlyWait() != 0) {
            dependencyContainer.driver.manage().timeouts().implicitlyWait(dependencyContainer.configFile.getImplicitlyWait(), TimeUnit.SECONDS);
            LOGGER.info("ImplicitlyWait ("+ dependencyContainer.configFile.getImplicitlyWait() +" second(s)) implemented successfully");
        }
    }

    /**
     * Quit web browser window(s) and WebDriver session.
     */
    private void quitWebDriver() {
        if (dependencyContainer.driver != null) {
            dependencyContainer.driver.quit();
            LOGGER.info("Web browser quit successfully");
        }
    }

}
