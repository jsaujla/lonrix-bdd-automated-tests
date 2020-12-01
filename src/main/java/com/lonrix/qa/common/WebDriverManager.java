package com.lonrix.qa.common;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * <h1>WebDriverManager Class</h1>
 * This class is used to centralize Selenium WebDriver objects at single place
 * This class provides Selenium WebDriver objects in ready form, which makes test scripts development easy
 * This class provides important logging by default
 * <p>
 * @author Jaspal Aujla
 */
public class WebDriverManager {

    //********** LOGGER OBJECT DECLARATION/INITIALIZATION **********
    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverManager.class);

    //********** OBJECT DECLARATION / INSTANCE VARIABLE **********
    private final int webDriverWaitTime;
    private final PropertiesManager propertiesManager;
    private WebDriver driver;

    /**
     * Parameterized Constructor
     *
     * @param propertiesManager
     */
    public WebDriverManager(PropertiesManager propertiesManager) {
        LOGGER.info("Constructing WebDriverManager class with parameter: " + propertiesManager.getClass());
        this.propertiesManager = propertiesManager;
        this.webDriverWaitTime = propertiesManager.getPropertyAsInt("web.driver.wait");
        initializeWebDriver();
    }

    /**
     * Get initialized WebDriver
     *
     * @return WebDriver
     */
    public WebDriver getDriver() {
        LOGGER.info("Get WebDriver");
        return driver;
    }

    /**
     * Get initialized WebDriverWait
     *
     * @param waitTimeInSeconds
     * @return WebDriverWait
     */
    public WebDriverWait getWebDriverWait(int... waitTimeInSeconds) {
        LOGGER.info("Get WebDriverWait");
        int waitTime = waitTimeInSeconds.length > 0 ? waitTimeInSeconds[0] : webDriverWaitTime;
        return new WebDriverWait(driver, waitTime);
    }

    /**
     * Get WebElement
     * @param locator
     * @return WebElement
     */
    public WebElement getWebElement(By locator) {
        try {
            WebElement webElement = driver.findElement(locator);
            LOGGER.info("Get WebElement successful with locator: " + locator);
            return webElement;
        } catch (Throwable throwable) {
            LOGGER.error("Get WebElement with locator (" + locator + ") failed. Exception occurred: " + throwable);
            throw throwable;
        }
    }

    /**
     * Get WebElements
     *
     * @param locator
     * @return List<WebElement>
     */
    public List<WebElement> getWebElements(By locator) {
        try {
            List<WebElement> webElements = driver.findElements(locator);
            LOGGER.info("Get WebElements successful with locator: " + locator);
            return webElements;
        } catch (Throwable throwable) {
            LOGGER.error("Get WebElements with locator (" + locator + ") failed. Exception occurred: " + throwable);
            throw throwable;
        }
    }

    /**
     * Wait until presence (if required) then get WebElement
     *
     * @param locator
     * @param waitTimeInSeconds
     * @return WebElement
     */
    public WebElement waitUntilPresenceThenGetWebElement(By locator, int... waitTimeInSeconds) {
        WebElement webElement;
        try{
            webElement = getWebDriverWait(waitTimeInSeconds).until(ExpectedConditions.presenceOfElementLocated(locator));
            LOGGER.info("Wait until presence then getWebElement successful with locator: " + locator);
            return webElement;
        } catch (Throwable throwable) {
            LOGGER.error("Wait until presence then get WebElement with locator (" + locator + ") failed. Exception occurred: " + throwable);
            throw throwable;
        }
    }

    /**
     * Wait until presence (if required) then get WebElements
     *
     * @param locator
     * @param waitTimeInSeconds
     * @return List<WebElement>
     */
    public List<WebElement> waitUntilPresenceThenGetWebElements(By locator, int... waitTimeInSeconds) {
        List<WebElement> webElements;
        try{
            webElements = getWebDriverWait(waitTimeInSeconds).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            LOGGER.info("Wait until presence then get WebElements successful with locator: " + locator);
            return webElements;
        } catch (Throwable throwable) {
            LOGGER.error("Wait until presence then get WebElements with locator (" + locator + ") failed. Exception occurred: " + throwable);
            throw throwable;
        }
    }

    /**
     * Wait until visibility (if required) then get WebElement
     *
     * @param locator
     * @param waitTimeInSeconds
     * @return WebElement
     */
    public WebElement waitUntilVisibilityThenGetWebElement(By locator, int... waitTimeInSeconds) {
        WebElement webElement;
        try{
            webElement = getWebDriverWait(waitTimeInSeconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
            LOGGER.info("Wait until visibility then get WebElement successful with locator: " + locator);
            return webElement;
        } catch (Throwable throwable) {
            LOGGER.error("Wait until visibility then get WebElement with locator (" + locator + ") failed. Exception occurred: " + throwable);
            throw throwable;
        }
    }

    /**
     * Wait until visibility (if required) then get WebElements
     *
     * @param locator
     * @param waitTimeInSeconds
     * @return List<WebElement>
     */
    public List<WebElement> waitUntilVisibilityThenGetWebElements(By locator, int... waitTimeInSeconds) {
        List<WebElement> webElements;
        try{
            webElements = getWebDriverWait(waitTimeInSeconds).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            LOGGER.info("Wait until visibility then get WebElements successful with locator: " + locator);
            return webElements;
        } catch (Throwable throwable) {
            LOGGER.error("Wait until visibility then get WebElements with locator (" + locator + ") failed. Exception occurred: " + throwable);
            throw throwable;
        }
    }

    /**
     * Wait until visibility and enabled (if required) then get WebElement
     *
     * @param locator
     * @param waitTimeInSeconds
     * @return WebElement
     */
    public WebElement waitUntilVisibilityAndEnabledThenGetWebElement(By locator, int... waitTimeInSeconds) {
        WebElement webElement;
        try{
            webElement = getWebDriverWait(waitTimeInSeconds).until(ExpectedConditions.elementToBeClickable(locator));
            LOGGER.info("Wait until visibility and enabled then get WebElement successful with locator: " + locator);
            return webElement;
        } catch (Throwable throwable) {
            LOGGER.error("Wait until visibility and enabled then get WebElement with locator (" + locator + ") failed. Exception occurred: " + throwable);
            throw throwable;
        }
    }

    /**
     * Get Select
     *
     * @param locator
     * @return Select
     */
    public Select getSelect(By locator) {
        LOGGER.info("Get Select WebElement with locator: " + locator);
        return new Select(getWebElement(locator));
    }

    /**
     * Wait until presence (if required) then get Select WebElement
     *
     * @param locator
     * @return Select
     */
    public Select waitUntilPresenceThenGetSelect(By locator) {
        LOGGER.info("Wait until presence then get Select WebElement with locator: " + locator);
        return new Select(waitUntilPresenceThenGetWebElement(locator));
    }

    /**
     * Wait until visibility (if required) then get Select WebElement
     *
     * @param locator
     * @return Select
     */
    public Select waitUntilVisibilityThenGetSelect(By locator) {
        LOGGER.info("Wait until visibility then get Select WebElement with locator: " + locator);
        return new Select(waitUntilVisibilityThenGetWebElement(locator));
    }

    /**
     * Wait until visibility and enabled (if required) then get Select WebElement
     *
     * @param locator
     * @return Select
     */
    public Select waitUntilVisibilityAndEnabledThenGetSelect(By locator) {
        LOGGER.info("Wait until visibility and enabled then get Select WebElement with locator: " + locator);
        return new Select(waitUntilVisibilityAndEnabledThenGetWebElement(locator));
    }

    /**
     * Get initialized Actions class
     *
     * @return Actions
     */
    public Actions getActions() {
        LOGGER.info("Get Actions");
        return new Actions(driver);
    }

    /**
     * Get Alert
     *
     * @param locator
     * @param waitTimeInSeconds
     * @return Alert
     */
    public Alert getAlert(By locator, int... waitTimeInSeconds) {
        Alert alert;
        try{
            alert = driver.switchTo().alert();
            LOGGER.info("Get Alert successful");
            return alert;
        } catch (Throwable throwable) {
            LOGGER.error("Get Alert failed. Exception occurred: " + throwable);
            throw throwable;
        }
    }

    /**
     * Wait until presence (if required) then get Alert
     *
     * @param waitTimeInSeconds
     * @return Alert
     */
    public Alert waitUntilPresenceThenGetAlert(int... waitTimeInSeconds) {
        Alert alert;
        try{
            alert = getWebDriverWait(waitTimeInSeconds).until(ExpectedConditions.alertIsPresent());
            LOGGER.info("Wait until present then get Alert successful");
            return alert;
        } catch (Throwable throwable) {
            LOGGER.error("Wait until present then get alert failed. Exception occurred: " + throwable);
            throw throwable;
        }
    }

    /**
     * Get JavascriptExecutor
     * @return JavascriptExecutor
     */
    public JavascriptExecutor getJavascriptExecutor() {
        LOGGER.info("Get JavascriptExecutor");
        return (JavascriptExecutor) driver;
    }

    /**
     * Launch/start web browser window and WebDriver session
     */
    private void initializeWebDriver() {
        LOGGER.info("Initializing WebDriver");
        try{
            if(propertiesManager.getProperty("web.browser.name").toLowerCase().contains("chrome")) {
                initializeChromeDriver();
            } else if(propertiesManager.getProperty("web.browser.name").toLowerCase().contains("firefox")) {
                initializeFirefoxDriver();
            } else if(propertiesManager.getProperty("web.browser.name").toLowerCase().contains("ie")
                    || propertiesManager.getProperty("web.browser.name").toLowerCase().contains("internet explorer")) {
                initializeIeDriver();
            } else if(propertiesManager.getProperty("web.browser.name").toLowerCase().contains("edge")) {
                initializeEdgeDriver();
            } else if(propertiesManager.getProperty("web.browser.name").toLowerCase().contains("opera")) {
                initializeOperaDriver();
            } else {
                LOGGER.error("Value of 'web.browser' in 'config properties' file should be: chrome, firefox, ie, edge or opera");
                throw new IllegalArgumentException("Value of 'web.browser' in 'config properties' file should be: chrome, firefox, ie, edge or opera");
            }
            LOGGER.info("Web browser (" + propertiesManager.getProperty("web.browser.name") + ") launched successfully");
        } catch (Throwable throwable) {
            LOGGER.error("Initialize WebDriver with browser name (" + propertiesManager.getProperty("web.browser.name") + ") failed. Exception occurred: " + throwable);
            throw throwable;
        }
    }

    private void initializeChromeDriver() {
        if(propertiesManager.getPropertyAsBoolean("web.driver.manager")) {
            io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
        } else {
            if(System.getProperty("webdriver.chrome.driver") == null) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browser-drivers/chromedriver.exe");
            }
        }
        driver = new ChromeDriver();
    }

    private void initializeFirefoxDriver() {
        if(propertiesManager.getPropertyAsBoolean("web.driver.manager")) {
            io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
        } else {
            if(System.getProperty("webdriver.gecko.driver") == null) {
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/browser-drivers/geckodriver.exe");
            }
        }
        driver = new FirefoxDriver();
    }

    private void initializeIeDriver() {
        if(propertiesManager.getPropertyAsBoolean("web.driver.manager")) {
            io.github.bonigarcia.wdm.WebDriverManager.iedriver().setup();
        } else {
            if(System.getProperty("webdriver.ie.driver") == null) {
                System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/browser-drivers/IEDriverServer.exe");
            }
        }
        driver = new InternetExplorerDriver();
    }

    private void initializeEdgeDriver() {
        if(propertiesManager.getPropertyAsBoolean("web.driver.manager")) {
            io.github.bonigarcia.wdm.WebDriverManager.edgedriver().setup();
        } else {
            if(System.getProperty("webdriver.edge.driver") == null) {
                System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "/browser-drivers/MicrosoftWebDriver.exe");
            }
        }
        driver = new EdgeDriver();
    }

    private void initializeOperaDriver() {
        if(propertiesManager.getPropertyAsBoolean("web.driver.manager")) {
            io.github.bonigarcia.wdm.WebDriverManager.operadriver().setup();
        } else {
            if(System.getProperty("webdriver.opera.driver") == null) {
                System.setProperty("webdriver.opera.driver", System.getProperty("user.dir") + "/browser-drivers/operadriver.exe");
            }
        }
        driver = new OperaDriver();
    }

}
