package com.lonrix.qa.pages;

import com.lonrix.qa.common.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Juno Viewer Strip Custom View Page</h1>
 * This class contains locators and actions related to Strip Custom View page.
 * <p>
 * @author Jaspal Aujla
 */
public class JunoViewerStripCustomViewPage {

    //********** LOGGER OBJECT DECLARATION/INITIALIZATION **********
    private static final Logger LOGGER = LoggerFactory.getLogger(JunoViewerStripCustomViewPage.class);

    private final WebDriverManager webDriverManager;

    private final By chooseLocationButtonLocator = By.id("btnGetDataScope");
    private final By chooseLocationNetworkDropdownLocator = By.id("lstNetworkNames");
    private final By chooseLocationShDropdownLocator = By.id("lstSection");
    private final By chooseLocationRsDropdownLocator = By.id("lstSubSection");
    private final By chooseLocationFromKmTextboxLocator = By.id("txtLocFrom");
    private final By chooseLocationToKmTextboxLocator = By.id("txtLocTo");
    private final By chooseLocationOkButtonLocator = By.id("btnDoDataScopeChange");
    private final By chooseLocationCloseButtonLocator = By.id("btnCancelDataScopeChange");

    public JunoViewerStripCustomViewPage(WebDriverManager webDriverManager) {
        this.webDriverManager = webDriverManager;
    }

    public void chooseLocation(String network, String sh, String rs, String fromKm, String toKm) {
        clickChooseLocationButton();
        if(network != null) {
            selectNetworkInChooseLocation(network);
        }
        if(sh != null) {
            selectShInChooseLocation(sh);
        }
        if(rs != null) {
            selectRsInChooseLocation(rs);
        }
        if(fromKm != null) {
            clearFromKmInChooseLocation();
            inputFromKmInChooseLocation(fromKm);
        }
        if(toKm != null) {
            clearToKmInChooseLocation();
            inputToKmInChooseLocation(toKm);
        }
        clickOkInChooseLocation();
    }

    public void clickChooseLocationButton() {
        webDriverManager.waitUntilVisibilityAndEnabledThenGetWebElement(chooseLocationButtonLocator).click();
    }

    public void selectNetworkInChooseLocation(String text) {
        webDriverManager.waitUntilVisibilityThenGetSelect(chooseLocationNetworkDropdownLocator).selectByVisibleText(text);
    }

    public void selectShInChooseLocation(String text) {
        webDriverManager.waitUntilVisibilityThenGetSelect(chooseLocationShDropdownLocator).selectByVisibleText(text);
    }

    public void selectRsInChooseLocation(String text) {
        webDriverManager.waitUntilVisibilityThenGetSelect(chooseLocationRsDropdownLocator).selectByVisibleText(text);
    }

    public void clearFromKmInChooseLocation() {
        webDriverManager.waitUntilVisibilityThenGetWebElement(chooseLocationFromKmTextboxLocator).clear();
    }

    public void inputFromKmInChooseLocation(String text) {
        webDriverManager.waitUntilVisibilityThenGetWebElement(chooseLocationFromKmTextboxLocator).sendKeys(text);
    }

    public void clearToKmInChooseLocation() {
        webDriverManager.waitUntilVisibilityThenGetWebElement(chooseLocationToKmTextboxLocator).clear();
    }

    public void inputToKmInChooseLocation(String text) {
        webDriverManager.waitUntilVisibilityThenGetWebElement(chooseLocationToKmTextboxLocator).sendKeys(text);
    }

    public void clickOkInChooseLocation() {
        webDriverManager.waitUntilVisibilityThenGetWebElement(chooseLocationOkButtonLocator).click();
    }

    public void clickCloseInChooseLocation() {
        webDriverManager.waitUntilVisibilityThenGetWebElement(chooseLocationCloseButtonLocator).click();
    }

    public List<String> getLwpIriVisibleCellsText(int expectedLwpIriVisibleCellsTextCount) {
        List<String> lwpIriCellsText = new ArrayList<>();
        for(int i=0; i<expectedLwpIriVisibleCellsTextCount; i++) {
            WebElement cellTextElement = webDriverManager.waitUntilVisibilityThenGetWebElement(By.xpath("(//*[@id='singleStripData_2']//*[text()])[" + (i+1) + "]"));
            LOGGER.info("Row: LWP IRI | Column: " + (i+1) + " -> Actual Cell Text: " + cellTextElement.getText());
            lwpIriCellsText.add(cellTextElement.getText());
        }
        return lwpIriCellsText;
    }

    public List<String> getLwpIriVisibleCellsColor(int expectedLwpIriVisibleCellsColorCount) {
        List<String> lwpIriCellsColor = new ArrayList<>();
        for(int i=0; i<expectedLwpIriVisibleCellsColorCount; i++) {
            WebElement cellColorElement = webDriverManager.waitUntilVisibilityThenGetWebElement(By.xpath("(//*[@id='singleStripData_2']//*[@fill and @stroke])[" + (i+1) + "]"));
            LOGGER.info("Row: LWP IRI | Column: " + (i+1) + " -> Actual Cell Color: " + cellColorElement.getAttribute("fill"));
            lwpIriCellsColor.add(cellColorElement.getAttribute("fill"));
        }
        return lwpIriCellsColor;
    }

    public List<String> getLwpIriAllCellsText (int expectedLwpIriAllCellsTextCount) {
        List<String> lwpIriAllCellsText = new ArrayList<>();
        for(int i=0; i<expectedLwpIriAllCellsTextCount; i++) {
            WebElement cellTextElement = webDriverManager.getWebElement(By.xpath("(//*[@id='singleStripData_2']//*[text()])[" + (i+1) + "]"));
            String cellText = (String) ((JavascriptExecutor) webDriverManager.getDriver()).executeScript("return arguments[0].innerHTML;",cellTextElement);
            LOGGER.info("Row: LWP IRI | Column: " + (i+1) + " -> Actual Cell Text: " + cellText);
            lwpIriAllCellsText.add(cellText);
        }
        return lwpIriAllCellsText;
    }

    public List<String> getLwpIriAllCellsColor (int expectedLwpIriAllCellsColorCount) {
        List<String> lwpIriAllCellsColor = new ArrayList<>();
        for(int i=0; i<expectedLwpIriAllCellsColorCount; i++) {
            WebElement cellColorElement = webDriverManager.getWebElement(By.xpath("(//*[@id='singleStripData_2']//*[@fill and @stroke])[" + (i+1) + "]"));
            String cellColor = (String) ((JavascriptExecutor) webDriverManager.getDriver()).executeScript("return arguments[0].getAttribute('fill');",cellColorElement);
            LOGGER.info("Row: LWP IRI | Column: " + (i+1) + " -> Actual Cell Color: " + cellColor);
            lwpIriAllCellsColor.add(cellColor);
        }
        return lwpIriAllCellsColor;
    }

    public List<String> getLwpMeanRutVisibleCellsText(int expectedLwpMeanRutVisibleCellsTextCount) {
        List<String> lwpMeanRutCellsText = new ArrayList<>();
        for(int i=0; i<expectedLwpMeanRutVisibleCellsTextCount; i++) {
            WebElement cellTextElement = webDriverManager.waitUntilVisibilityThenGetWebElement(By.xpath("(//*[@id='singleStripData_1']//*[text()])[" + (i+1) + "]"));
            LOGGER.info("Row: LWP Mean Rut | Column: " + (i+1) + " -> Actual Cell Text: " + cellTextElement.getText());
            lwpMeanRutCellsText.add(cellTextElement.getText());
        }
        return lwpMeanRutCellsText;
    }

    public List<String> getLwpMeanRutVisibleCellsColor(int expectedLwpMeanRutVisibleCellsColorCount) {
        List<String> lwpMeanRutCellsColor = new ArrayList<>();
        for(int i=0; i<expectedLwpMeanRutVisibleCellsColorCount; i++) {
            WebElement cellColorElement = webDriverManager.waitUntilVisibilityThenGetWebElement(By.xpath("(//*[@id='singleStripData_1']//*[@fill and @stroke])[" + (i+1) + "]"));
            LOGGER.info("Row: LWP Mean Rut | Column: " + (i+1) + " -> Actual Cell Color: " + cellColorElement.getAttribute("fill"));
            lwpMeanRutCellsColor.add(cellColorElement.getAttribute("fill"));
        }
        return lwpMeanRutCellsColor;
    }

    public List<String> getSurfacingCodeVisibleCellsText(int expectedSurfacingCodeVisibleCellsTextCount) {
        List<String> surfacingCodeCellsText = new ArrayList<>();
        for(int i=0; i<expectedSurfacingCodeVisibleCellsTextCount; i++) {
            WebElement cellTextElement = webDriverManager.waitUntilVisibilityThenGetWebElement(By.xpath("(//*[@id='singleStripData_0']//*[text()])[" + (i+1) + "]"));
            LOGGER.info("Row: Surfacing Code | Column: " + (i+1) + " -> Actual Cell Text: " + cellTextElement.getText());
            surfacingCodeCellsText.add(cellTextElement.getText());
        }
        return surfacingCodeCellsText;
    }

    public List<String> getSurfacingCodeVisibleCellsColor(int expectedSurfacingCodeVisibleCellsColorCount) {
        List<String> surfacingCodeCellsColor = new ArrayList<>();
        for(int i=0; i<expectedSurfacingCodeVisibleCellsColorCount; i++) {
            WebElement cellColorElement = webDriverManager.waitUntilVisibilityThenGetWebElement(By.xpath("(//*[@id='singleStripData_0']//*[@fill and @stroke='Black'])[" + (i+1) + "]"));
            LOGGER.info("Row: Surfacing Code | Column: " + (i+1) + " ->  Actual Cell Color: " + cellColorElement.getAttribute("fill"));
            surfacingCodeCellsColor.add(cellColorElement.getAttribute("fill"));
        }
        return surfacingCodeCellsColor;
    }

    public List<String> getAccidentsVisibleCellsText(int expectedAccidentsVisibleCellsTextCount) {
        List<String> accidentsCellsText = new ArrayList<>();
        for(int i=0; i<expectedAccidentsVisibleCellsTextCount; i++) {
            WebElement cellTextElement = webDriverManager.waitUntilVisibilityThenGetWebElement(By.xpath("(//*[@id='singleStripData_3']//*[text()])[" + (i+1) + "]"));
            LOGGER.info("Row: Accidents | Column: " + (i+1) + " -> Actual Cell Text: " + cellTextElement.getText());
            accidentsCellsText.add(cellTextElement.getText());
        }
        return accidentsCellsText;
    }

    public List<String> getAccidentsVisibleCellsColor(int expectedAccidentsVisibleCellsColorCount) {
        List<String> accidentsCellsColor = new ArrayList<>();
        for(int i=0; i<expectedAccidentsVisibleCellsColorCount; i++) {
            WebElement cellColorElement = webDriverManager.waitUntilVisibilityThenGetWebElement(By.xpath("(//*[@id='singleStripData_3']//*[@fill and @stroke])[" + (i+1) + "]"));
            LOGGER.info("Row: Accidents | Column: " + (i+1) + " -> Actual Cell Color: " + cellColorElement.getAttribute("fill"));
            accidentsCellsColor.add(cellColorElement.getAttribute("fill"));
        }
        return accidentsCellsColor;
    }

    public List<String> getNaasraTextInRow1 (int expectedNaasraTextInRow1Count) {
        List<String> naasraTextInRow1 = new ArrayList<>();
        for(int i=0; i<expectedNaasraTextInRow1Count; i++) {
            WebElement row1TextElement = webDriverManager.waitUntilVisibilityThenGetWebElement(By.xpath("(//*[@id='pnl1_highLow_" + i + "HLBar']//*[text()])[1]"));
            LOGGER.info("Naasra | Row 1 -> Actual Column " + (i+1) + " Text: " + row1TextElement.getText());
            naasraTextInRow1.add(row1TextElement.getText());
        }
        return naasraTextInRow1;
    }

    public List<String> getNaasraTextInRow2 (int expectedNaasraTextInRow2Count) {
        List<String> naasraTextInRow2 = new ArrayList<>();
        for(int i=0; i<expectedNaasraTextInRow2Count; i++) {
            WebElement row2TextElement = webDriverManager.waitUntilVisibilityThenGetWebElement(By.xpath("(//*[@id='pnl1_highLow_" + i + "HLBar']//*[text()])[2]"));
            LOGGER.info("Naasra | Row 2 -> Actual Column " + (i+1) + " Text: " + row2TextElement.getText());
            naasraTextInRow2.add(row2TextElement.getText());
        }
        return naasraTextInRow2;
    }

}
