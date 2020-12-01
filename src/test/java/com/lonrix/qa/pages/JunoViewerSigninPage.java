package com.lonrix.qa.pages;

import com.lonrix.qa.common.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * <h1>Juno Viewer Signin Page</h1>
 * This class contains locators and actions related to Signin page.
 * <p>
 * @author Jaspal Aujla
 */
public class JunoViewerSigninPage {

    private final WebDriverManager webDriverManager;

    private final By usernameTextboxLocator = By.id("txtUserName");
    private final By passwordTextboxLocator = By.id("txtPassword");
    private final By rememberMeCheckboxLocator = By.id("chkRememberMe");
    private final By signinButtonLocator = By.id("btnSubmit");

    public JunoViewerSigninPage(WebDriverManager webDriverManager) {
        this.webDriverManager = webDriverManager;
    }

    public void open(String baseUrl) {
        webDriverManager.getDriver().get(baseUrl);
    }

    public void signin(String username, String password, boolean rememberMe) {
        webDriverManager.waitUntilVisibilityThenGetWebElement(usernameTextboxLocator).sendKeys(username);
        webDriverManager.waitUntilVisibilityThenGetWebElement(passwordTextboxLocator).sendKeys(password);
        WebElement rememberMeCheckboxElement = webDriverManager.waitUntilVisibilityThenGetWebElement(rememberMeCheckboxLocator);
        if(rememberMe && !rememberMeCheckboxElement.isSelected()) {
            rememberMeCheckboxElement.click();
        }
        webDriverManager.waitUntilVisibilityThenGetWebElement(signinButtonLocator).click();
    }

}
