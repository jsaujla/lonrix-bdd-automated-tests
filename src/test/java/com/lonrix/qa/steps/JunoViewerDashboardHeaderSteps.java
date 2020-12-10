package com.lonrix.qa.steps;

import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Steps definition - Dashboard Header</h1>
 * This class includes implementation of step definition correspondence to feature files.
 * <p>
 * @author Jaspal Aujla
 */
public class JunoViewerDashboardHeaderSteps extends PageInitializer {

    //********** LOGGER OBJECT DECLARATION/INITIALIZATION **********
    private static final Logger LOGGER = LoggerFactory.getLogger(JunoViewerDashboardHeaderSteps.class);

    /**
     * Parameterized Constructor
     *
     * @param dependencyContainer
     */
    public JunoViewerDashboardHeaderSteps(DependencyContainer dependencyContainer) {
        super(dependencyContainer);
    }

    //********** STEP DEFINITION METHODS **********

    @When("I goto to {string}")
    public void i_goto_to(String string) throws InterruptedException {
        LOGGER.info("When I goto to {string}");
        getJunoViewerDashboardHeader().navigateToViewsThen("Strip Map View", "");
        Thread.sleep(500);
    }

}
