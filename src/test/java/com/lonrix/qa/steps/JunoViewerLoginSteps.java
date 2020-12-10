package com.lonrix.qa.steps;

import io.cucumber.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Steps definition - Login</h1>
 * This class includes implementation of step definition correspondence to feature files.
 * <p>
 * @author Jaspal Aujla
 */
public class JunoViewerLoginSteps extends PageInitializer {

    //********** LOGGER OBJECT DECLARATION/INITIALIZATION **********
    private static final Logger LOGGER = LoggerFactory.getLogger(JunoViewerLoginSteps.class);

    /**
     * Parameterized Constructor
     *
     * @param dependencyContainer
     */
    public JunoViewerLoginSteps(DependencyContainer dependencyContainer) {
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

}
