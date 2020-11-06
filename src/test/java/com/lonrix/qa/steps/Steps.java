package com.lonrix.qa.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

/**
 * <h1>Steps definition</h1>
 * This class includes implementation of step definition correspondence to feature files.
 * <p>
 * @author Jaspal
 */
public class Steps {

    //********** LOGGER OBJECT DECLARATION/INITIALIZATION **********
    private static final Logger LOGGER = LoggerFactory.getLogger(Steps.class);

    //********** OBJECT DECLARATION **********
    private DependencyContainer dependencyContainer;

    //********** CONSTRUCTOR **********
    public Steps(DependencyContainer dependencyContainer) {
        this.dependencyContainer = dependencyContainer;
    }


    //********** STEP DEFINITION METHODS **********

    @Given("I am at JunoViewer login page")
    public void i_am_at_JunoViewer_login_page() {
        LOGGER.info("Given I am at JunoViewer login page");
        dependencyContainer.driver.get(dependencyContainer.configFile.getJunoViewerBaseUrl());
    }

    @Given("I login JunoViewer with user: {string} and password: {string}")
    public void i_login_JunoViewer_with_user_and_password(String username, String password) {
        LOGGER.info("Given I login JunoViewer with user: {string} and password: {string}");
        if(username.startsWith("${") && username.endsWith("}")) {
            dependencyContainer.driver.findElement(By.id("txtUserName")).sendKeys(
                    dependencyContainer.configFile.getPropertyKey(username.replace("${", "").replace("}", "")));
        } else {
            dependencyContainer.driver.findElement(By.id("txtUserName")).sendKeys(username);
        }
        if(password.startsWith("${") && password.endsWith("}")) {
            dependencyContainer.driver.findElement(By.id("txtPassword")).sendKeys(
                    dependencyContainer.configFile.getPropertyKey(password.replace("${", "").replace("}", "")));
        } else {
            dependencyContainer.driver.findElement(By.id("txtPassword")).sendKeys(username);
        }
        dependencyContainer.driver.findElement(By.id("btnSubmit")).click();
    }

    @When("I goto to {string}")
    public void i_goto_to(String string) throws InterruptedException {
        // TODO The implementation of this method will be dynamically changed.
        LOGGER.info("When I goto to {string}");
        dependencyContainer.driver.findElement(By.xpath("//a[text()='Views ']")).click();
        dependencyContainer.driver.findElement(By.linkText("Strip Map View")).click();
        Thread.sleep(500);
    }

    @When("I click on Choose Location button")
    public void i_click_on_Choose_Location_button() {
        LOGGER.info("When I click on Choose Location button");
        WebDriverWait wait = new WebDriverWait(dependencyContainer.driver, dependencyContainer.configFile.getWebDriverWait());
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnGetDataScope"))).click();
    }

    @When("I choose location and click OK")
    public void i_choose_location_and_click_OK(io.cucumber.datatable.DataTable dataTable) {
        LOGGER.info("When I choose location and click OK");
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        if(rows.get(0).get("Network:") != null) {
            WebDriverWait wait = new WebDriverWait(dependencyContainer.driver, dependencyContainer.configFile.getWebDriverWait());
            WebElement networkNamesDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lstNetworkNames")));
            Select selectNetworkNamesDropdown = new Select(networkNamesDropdown);
            selectNetworkNamesDropdown.selectByVisibleText("Network A");
        }
        if(rows.get(0).get("SH:") != null) {
            // TODO Implementation, which is not required for existing demonstration.
        }
        if(rows.get(0).get("RS:") != null) {
            WebElement lstSubSectionDropdown = dependencyContainer.driver.findElement(By.id("lstSubSection"));
            Select selectLstSubSectionDropdown = new Select(lstSubSectionDropdown);
            selectLstSubSectionDropdown.selectByVisibleText("103");
        }
        if(rows.get(0).get("Section Id:") != null) {
            // TODO Implementation, which is not required for existing demonstration.
        }
        if(rows.get(0).get("From Km:") != null) {
            // TODO Implementation, which is not required for existing demonstration.
        }
        if(rows.get(0).get("To Km:") != null) {
            // TODO Implementation, which is not required for existing demonstration.
        }
        dependencyContainer.driver.findElement(By.id("btnDoDataScopeChange")).click();
    }

    @Then("I should see expectedLwpIriCellText and expectedLwpIriCellColor")
    public void i_should_see_expectedLwpIriCellText_and_expectedLwpIriCellColor(io.cucumber.datatable.DataTable dataTable) {
        LOGGER.info("Then I should see expectedLwpIriCellText and expectedLwpIriCellColor");
        List<List<String>> list = dataTable.asLists();
        SoftAssert softAssert = new SoftAssert();
        for(int i=0; i<list.get(0).size(); i++) {
            WebElement cellTextElement = dependencyContainer.driver.findElement(By.xpath("(//*[@id='singleStripData_2']//*[text()])[" + (i+1) + "]"));
            WebElement cellColorElement = dependencyContainer.driver.findElement(By.xpath("(//*[@id='singleStripData_2']//*[@fill and @stroke])[" + (i+1) + "]"));
            LOGGER.info("Row: LWP IRI | Column: " + (i+1) + " -> Actual Cell Text: " + cellTextElement.getText() + " | Actual Cell Color: " + cellColorElement.getAttribute("fill"));
            softAssert.assertEquals(cellTextElement.getText(), list.get(0).get(i));
            softAssert.assertEquals(cellColorElement.getAttribute("fill"), list.get(1).get(i));
        }
        softAssert.assertAll();
    }

    @Then("I should see expectedLwpMeanRutCellText and expectedLwpMeanRutCellColor")
    public void i_should_see_expectedLwpMeanRutCellText_and_expectedLwpMeanRutCellColor(io.cucumber.datatable.DataTable dataTable) {
        LOGGER.info("Then I should see expectedLwpMeanRutCellText and expectedLwpMeanRutCellColor");
        List<List<String>> list = dataTable.asLists();
        SoftAssert softAssert = new SoftAssert();
        for(int i=0; i<list.get(0).size(); i++) {
            WebElement cellTextElement = dependencyContainer.driver.findElement(By.xpath("(//*[@id='singleStripData_1']//*[text()])[" + (i+1) + "]"));
            WebElement cellColorElement = dependencyContainer.driver.findElement(By.xpath("(//*[@id='singleStripData_1']//*[@fill and @stroke])[" + (i+1) + "]"));
            LOGGER.info("Row: LWP Mean Rut | Column: " + (i+1) + " -> Actual Cell Text: " + cellTextElement.getText() + " | Actual Cell Color: " + cellColorElement.getAttribute("fill"));
            softAssert.assertEquals(cellTextElement.getText(), list.get(0).get(i));
            softAssert.assertEquals(cellColorElement.getAttribute("fill"), list.get(1).get(i));
        }
        softAssert.assertAll();
    }

    @Then("I should see expectedSurfacingCodeCellText and expectedSurfacingCodeCellColor")
    public void i_should_see_expectedSurfacingCodeCellText_and_expectedSurfacingCodeCellColor(io.cucumber.datatable.DataTable dataTable) {
        LOGGER.info("Then I should see expectedSurfacingCodeCellText and expectedSurfacingCodeCellColor");
        List<List<String>> list = dataTable.asLists();
        SoftAssert softAssert = new SoftAssert();
        for(int i=0; i<list.get(0).size(); i++) {
            WebElement cellTextElement = dependencyContainer.driver.findElement(By.xpath("(//*[@id='singleStripData_0']//*[text()])[" + (i+1) + "]"));
            WebElement cellColorElement = dependencyContainer.driver.findElement(By.xpath("(//*[@id='singleStripData_0']//*[@fill and @stroke='Black'])[" + (i+1) + "]"));
            LOGGER.info("Row: Surfacing Code | Column: " + (i+1) + " -> Actual Cell Text: " + cellTextElement.getText() + " | Actual Cell Color: " + cellColorElement.getAttribute("fill"));
            softAssert.assertEquals(cellTextElement.getText(), list.get(0).get(i));
            softAssert.assertEquals(cellColorElement.getAttribute("fill"), list.get(1).get(i));
        }
        softAssert.assertAll();
    }

    @Then("I should see expectedAccidentsCellText and expectedAccidentsCellColor")
    public void i_should_see_expectedAccidentsCellText_and_expectedAccidentsCellColor(io.cucumber.datatable.DataTable dataTable) {
        LOGGER.info("Then I should see expectedAccidentsCellText and expectedAccidentsCellColor");
        List<List<String>> list = dataTable.asLists();
        SoftAssert softAssert = new SoftAssert();
        for(int i=0; i<list.get(0).size(); i++) {
            WebElement cellTextElement = dependencyContainer.driver.findElement(By.xpath("(//*[@id='singleStripData_3']//*[text()])[" + (i+1) + "]"));
            WebElement cellColorElement = dependencyContainer.driver.findElement(By.xpath("(//*[@id='singleStripData_3']//*[@fill and @stroke])[" + (i+1) + "]"));
            LOGGER.info("Row: Accidents | Column: " + (i+1) + " -> Actual Cell Text: " + cellTextElement.getText() + " | Actual Cell Color: " + cellColorElement.getAttribute("fill"));
            softAssert.assertEquals(cellTextElement.getText(), list.get(0).get(i));
            softAssert.assertEquals(cellColorElement.getAttribute("fill"), list.get(1).get(i));
        }
        softAssert.assertAll();
    }

    @Then("Verify presence of expectedLwpIriCellText and expectedLwpIriCellColor")
    public void verify_presence_of_expectedLwpIriCellText_and_expectedLwpIriCellColor(io.cucumber.datatable.DataTable dataTable) {
        LOGGER.info("Then Verify presence of expectedLwpIriCellText and expectedLwpIriCellColor");
        List<List<String>> list = dataTable.asLists();
        SoftAssert softAssert = new SoftAssert();
        for(int i=0; i<list.get(0).size(); i++) {
            WebElement cellTextElement = dependencyContainer.driver.findElement(By.xpath("(//*[@id='singleStripData_2']//*[text()])[" + (i+1) + "]"));
            WebElement cellColorElement = dependencyContainer.driver.findElement(By.xpath("(//*[@id='singleStripData_2']//*[@fill and @stroke])[" + (i+1) + "]"));
            String cellText = (String) ((JavascriptExecutor) dependencyContainer.driver).executeScript("return arguments[0].innerHTML;",cellTextElement);
            String cellColor = (String) ((JavascriptExecutor) dependencyContainer.driver).executeScript("return arguments[0].getAttribute('fill');",cellColorElement);
            LOGGER.info("Row: LWP IRI | Column: " + (i+1) + " -> Actual Cell Text: " + cellText + " | Actual Cell Color: " + cellColor);
            softAssert.assertEquals(cellText, list.get(0).get(i));
            softAssert.assertEquals(cellColor, list.get(1).get(i));
        }
        softAssert.assertAll();
    }

    @Then("Verify presence of expectedLwpMeanRutCellText and expectedLwpMeanRutCellColor")
    public void verify_presence_of_expectedLwpMeanRutCellText_and_expectedLwpMeanRutCellColor(io.cucumber.datatable.DataTable dataTable) {
        // TODO Implementation, which is not required for existing demonstration.
    }

    @Then("Verify presence of expectedSurfacingCodeCellText and expectedSurfacingCodeCellColor")
    public void verify_presence_of_expectedSurfacingCodeCellText_and_expectedSurfacingCodeCellColor(io.cucumber.datatable.DataTable dataTable) {
        // TODO Implementation, which is not required for existing demonstration.
    }

    @Then("Verify presence of expectedAccidentsCellText and expectedAccidentsCellColor")
    public void verify_presence_of_expectedAccidentsCellText_and_expectedAccidentsCellColor(io.cucumber.datatable.DataTable dataTable) {
        // TODO Implementation, which is not required for existing demonstration.
    }

    @Then("I should see expectedNaasraTextInRow1")
    public void i_should_see_expectedNaasraTextInRow1(io.cucumber.datatable.DataTable dataTable) {
        LOGGER.info("Then I should see expectedNaasraTextInRow1");
        List<List<String>> list = dataTable.asLists();
        SoftAssert softAssert = new SoftAssert();
        for(int i=0; i<list.get(0).size(); i++) {
            WebElement row1TextElement = dependencyContainer.driver.findElement(By.xpath("(//*[@id='pnl1_highLow_" + i + "HLBar']//*[text()])[1]"));
            LOGGER.info("Naasra | Row 1 -> Actual Column " + (i+1) + " Text: " + row1TextElement.getText());
            softAssert.assertEquals(row1TextElement.getText(), list.get(0).get(i));
        }
        softAssert.assertAll();
    }

    @Then("I should see expectedNaasraTextInRow2")
    public void i_should_see_expectedNaasraTextInRow2(io.cucumber.datatable.DataTable dataTable) {
        LOGGER.info("Then I should see expectedNaasraTextInRow2");
        List<List<String>> list = dataTable.asLists();
        SoftAssert softAssert = new SoftAssert();
        for(int i=0; i<list.get(0).size(); i++) {
            WebElement row2TextElement = dependencyContainer.driver.findElement(By.xpath("(//*[@id='pnl1_highLow_" + i + "HLBar']//*[text()])[2]"));
            LOGGER.info("Naasra | Row 2 -> Actual Column " + (i+1) + " Text: " + row2TextElement.getText());
            softAssert.assertEquals(row2TextElement.getText(), list.get(0).get(i));
        }
        softAssert.assertAll();
    }

}
