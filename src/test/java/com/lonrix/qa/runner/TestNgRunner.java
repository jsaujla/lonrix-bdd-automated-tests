package com.lonrix.qa.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * <h1>Test Runner With TestNG</h1>
 * This class is used to add Cucumber options and run the test(s) with TestNG.
 * <p>
 * @author Jaspal Aujla
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.lonrix.qa.steps"},
        monochrome = true,
        dryRun = false,
        tags={"@juno"},
        plugin = {"pretty",
                "html:target/cucumber-html-report",
                "json:target/cucumber.json"
        }
)
public class TestNgRunner extends AbstractTestNGCucumberTests {

}
