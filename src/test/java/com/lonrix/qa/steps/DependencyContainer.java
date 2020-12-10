package com.lonrix.qa.steps;

import com.lonrix.qa.common.PropertiesManager;
import com.lonrix.qa.common.WebDriverManager;

/**
 * <h1>Dependency Container Class for Hooks and Steps</h1>
 * This class is used to keep common object declarations and the initialized object scope accessible between Hooks, PageInitializer and Steps classes.
 * <p>
 * @author Jaspal Aujla
 */
public class DependencyContainer {

    //*************OBJECT DECLARATION*************
    protected PropertiesManager propertiesManager;
    protected WebDriverManager webDriverManager;

}
