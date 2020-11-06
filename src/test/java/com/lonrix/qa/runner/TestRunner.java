package com.lonrix.qa.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * <h1>Test Runner</h1>
 * This class is used to add Cucumber options and run the tests.
 * <p>
 * @author Jaspal
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/resources/features",
        glue={"com.lonrix.qa.steps"},
        monochrome = true,
        plugin={"pretty",
                "html:target/cucumber-html-report",
                "json:target/cucumber.json",
        },
        tags={"@juno"}
)
public class TestRunner {

}
