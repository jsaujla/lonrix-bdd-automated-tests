package com.lonrix.qa.pages;

import com.lonrix.qa.common.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * <h1>Juno Viewer Dashboard Page</h1>
 * This class contains locators and actions related to 'header' at user dashboard page.
 * <p>
 * @author Jaspal Aujla
 */
public class JunoViewerDashboardHeader {

    private final WebDriverManager webDriverManager;

    private final By viewsMenuLocator = By.xpath("//a[@class='dropdown-toggle' and text()='Views ']");

    public JunoViewerDashboardHeader(WebDriverManager webDriverManager) {
        this.webDriverManager = webDriverManager;
    }

    public void navigateToViewsThen(String menuOption, String subMenuOption) {
        WebElement viewsMenuElement = webDriverManager.waitUntilVisibilityThenGetWebElement(viewsMenuLocator);
        viewsMenuElement.click();
        if(subMenuOption == null || subMenuOption.equals("")) {
            webDriverManager.waitUntilVisibilityThenGetWebElement(By.linkText(menuOption)).click();
        } else {
            webDriverManager.getActions().moveToElement(webDriverManager.waitUntilVisibilityThenGetWebElement(By.linkText(menuOption))).perform();
            webDriverManager.waitUntilVisibilityThenGetWebElement(By.xpath("//ul[@class='dropdown-menu']/li/a[text()='"+subMenuOption+"']")).click();
        }
    }

}
