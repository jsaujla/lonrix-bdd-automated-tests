package com.lonrix.qa.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
public class Steps extends PageInitializer {

    //********** LOGGER OBJECT DECLARATION/INITIALIZATION **********
    private static final Logger LOGGER = LoggerFactory.getLogger(Steps.class);

    //********** CONSTRUCTOR **********
    public Steps(DependencyContainer dependencyContainer) {
        super(dependencyContainer);
    }

    //********** STEP DEFINITION METHODS **********

    @Given("I am at JunoViewer login page")
    public void i_am_at_JunoViewer_login_page() {
        LOGGER.info("Given I am at JunoViewer login page");
        getSigninPage().open(propertiesManager.getProperty("juno.viewer.base.url"));
    }

    @Given("I login JunoViewer with user: {string} and password: {string}")
    public void i_login_JunoViewer_with_user_and_password(String username, String password) {
        LOGGER.info("Given I login JunoViewer with user: {string} and password: {string}");
        loginStep(username, password, false);
    }

    @Given("I login JunoViewer with user: {string}, password: {string} and rememberme: {string}")
    public void i_login_JunoViewer_with_user_password_and_rememberme(String username, String password, String rememberMe) {
        LOGGER.info("Given I login JunoViewer with user: {string}, password: {string} and rememberme: {string}");
        loginStep(username, password, Boolean.parseBoolean(rememberMe));
    }

    private void loginStep(String username, String password, boolean rememberMe) {
        String usernameLocal;
        String passwordLocal;
        if(username.startsWith("${") && username.endsWith("}")) {
            usernameLocal = propertiesManager.getProperty(username.replace("${", "").replace("}", ""));
        } else {
            usernameLocal = username;
        }
        if(password.startsWith("${") && password.endsWith("}")) {
            passwordLocal = propertiesManager.getProperty(password.replace("${", "").replace("}", ""));
        } else {
            passwordLocal = password;
        }
        getSigninPage().signin(usernameLocal, passwordLocal, rememberMe);
    }

    @When("I goto to {string}")
    public void i_goto_to(String string) throws InterruptedException {
        LOGGER.info("When I goto to {string}");
        getJunoViewerDashboardHeader().navigateToViewsThen("Strip Map View", "");
        Thread.sleep(500);
    }

    @When("I choose location and click OK")
    public void i_choose_location_and_click_OK(io.cucumber.datatable.DataTable dataTable) {
        LOGGER.info("When I choose location and click OK");
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        getStripCustomViewPage().chooseLocation(rows.get(0).get("Network:"), rows.get(0).get("SH:"),
                rows.get(0).get("RS:"), rows.get(0).get("From Km:"), rows.get(0).get("To Km:"));
    }

    @Then("I should see expectedLwpIriCellText and expectedLwpIriCellColor")
    public void i_should_see_expectedLwpIriCellText_and_expectedLwpIriCellColor(io.cucumber.datatable.DataTable dataTable) {
        LOGGER.info("Then I should see expectedLwpIriCellText and expectedLwpIriCellColor");
        List<List<String>> expectedData = dataTable.asLists();

        List<String> actualLwpIriCellText = getStripCustomViewPage().getLwpIriVisibleCellsText(expectedData.get(0).size());
        List<String> actualLwpIriCellColor = getStripCustomViewPage().getLwpIriVisibleCellsColor(expectedData.get(1).size());

        SoftAssert softAssert = new SoftAssert();
        for(int i=0; i<expectedData.get(0).size(); i++) {
            softAssert.assertEquals(actualLwpIriCellText.get(i), expectedData.get(0).get(i));
            softAssert.assertEquals(actualLwpIriCellColor.get(i), expectedData.get(1).get(i));
        }
        softAssert.assertAll();
    }

    @Then("I should see expectedLwpMeanRutCellText and expectedLwpMeanRutCellColor")
    public void i_should_see_expectedLwpMeanRutCellText_and_expectedLwpMeanRutCellColor(io.cucumber.datatable.DataTable dataTable) {
        LOGGER.info("Then I should see expectedLwpMeanRutCellText and expectedLwpMeanRutCellColor");
        List<List<String>> expectedData = dataTable.asLists();

        List<String> actualLwpMeanRutCellText = getStripCustomViewPage().getLwpMeanRutVisibleCellsText(expectedData.get(0).size());
        List<String> actualLwpMeanRutCellColor = getStripCustomViewPage().getLwpMeanRutVisibleCellsColor(expectedData.get(1).size());

        SoftAssert softAssert = new SoftAssert();
        for(int i=0; i<expectedData.get(0).size(); i++) {
            softAssert.assertEquals(actualLwpMeanRutCellText.get(i), expectedData.get(0).get(i));
            softAssert.assertEquals(actualLwpMeanRutCellColor.get(i), expectedData.get(1).get(i));
        }
        softAssert.assertAll();
    }

    @Then("I should see expectedSurfacingCodeCellText and expectedSurfacingCodeCellColor")
    public void i_should_see_expectedSurfacingCodeCellText_and_expectedSurfacingCodeCellColor(io.cucumber.datatable.DataTable dataTable) {
        LOGGER.info("Then I should see expectedSurfacingCodeCellText and expectedSurfacingCodeCellColor");
        List<List<String>> expectedData = dataTable.asLists();

        List<String> actualSurfacingCodeCellText = getStripCustomViewPage().getSurfacingCodeVisibleCellsText(expectedData.get(0).size());
        List<String> actualSurfacingCodeCellColor = getStripCustomViewPage().getSurfacingCodeVisibleCellsColor(expectedData.get(1).size());

        SoftAssert softAssert = new SoftAssert();
        for(int i=0; i<expectedData.get(0).size(); i++) {
            softAssert.assertEquals(actualSurfacingCodeCellText.get(i), expectedData.get(0).get(i));
            softAssert.assertEquals(actualSurfacingCodeCellColor.get(i), expectedData.get(1).get(i));
        }
        softAssert.assertAll();
    }

    @Then("I should see expectedAccidentsCellText and expectedAccidentsCellColor")
    public void i_should_see_expectedAccidentsCellText_and_expectedAccidentsCellColor(io.cucumber.datatable.DataTable dataTable) {
        LOGGER.info("Then I should see expectedAccidentsCellText and expectedAccidentsCellColor");
        List<List<String>> expectedData = dataTable.asLists();

        List<String> actualAccidentsCellText = getStripCustomViewPage().getAccidentsVisibleCellsText(expectedData.get(0).size());
        List<String> actualAccidentsCellColor = getStripCustomViewPage().getAccidentsVisibleCellsColor(expectedData.get(1).size());

        SoftAssert softAssert = new SoftAssert();
        for(int i=0; i<expectedData.get(0).size(); i++) {
            softAssert.assertEquals(actualAccidentsCellText.get(i), expectedData.get(0).get(i));
            softAssert.assertEquals(actualAccidentsCellColor.get(i), expectedData.get(1).get(i));
        }
        softAssert.assertAll();
    }

    @Then("Verify presence of expectedLwpIriCellText and expectedLwpIriCellColor")
    public void verify_presence_of_expectedLwpIriCellText_and_expectedLwpIriCellColor(io.cucumber.datatable.DataTable dataTable) {
        LOGGER.info("Then Verify presence of expectedLwpIriCellText and expectedLwpIriCellColor");
        List<List<String>> expectedData = dataTable.asLists();

        List<String> actualLwpIriAllCellsText = getStripCustomViewPage().getLwpIriAllCellsText(expectedData.get(0).size());
        List<String> actualLwpIriAllCellsColor = getStripCustomViewPage().getLwpIriAllCellsColor(expectedData.get(1).size());

        SoftAssert softAssert = new SoftAssert();
        for(int i=0; i<expectedData.get(0).size(); i++) {
            softAssert.assertEquals(actualLwpIriAllCellsText.get(i), expectedData.get(0).get(i));
            softAssert.assertEquals(actualLwpIriAllCellsColor.get(i), expectedData.get(1).get(i));
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
        List<List<String>> expectedData = dataTable.asLists();

        List<String> actualNaasraTextInRow1 = getStripCustomViewPage().getNaasraTextInRow1(expectedData.get(0).size());

        SoftAssert softAssert = new SoftAssert();
        for(int i=0; i<expectedData.get(0).size(); i++) {
            softAssert.assertEquals(actualNaasraTextInRow1.get(i), expectedData.get(0).get(i));
        }
        softAssert.assertAll();
    }

    @Then("I should see expectedNaasraTextInRow2")
    public void i_should_see_expectedNaasraTextInRow2(io.cucumber.datatable.DataTable dataTable) {
        LOGGER.info("Then I should see expectedNaasraTextInRow2");
        List<List<String>> expectedData = dataTable.asLists();

        List<String> actualNaasraTextInRow2 = getStripCustomViewPage().getNaasraTextInRow2(expectedData.get(0).size());

        SoftAssert softAssert = new SoftAssert();
        for(int i=0; i<expectedData.get(0).size(); i++) {
            softAssert.assertEquals(actualNaasraTextInRow2.get(i), expectedData.get(0).get(i));
        }
        softAssert.assertAll();
    }

}
