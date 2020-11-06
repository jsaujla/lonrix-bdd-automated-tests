package com.lonrix.qa.steps;

import com.lonrix.qa.utils.ReadConfig;
import org.openqa.selenium.WebDriver;

/**
 * <h1>Dependency Container Class for Hooks and Steps</h1>
 * This class is used to keep common object declarations and the initialized object scope accessible between Hooks and Steps classes.
 * <p>
 * @author Jaspal
 */
public class DependencyContainer {

    //*************OBJECT DECLARATION*************
    protected ReadConfig configFile;
    protected WebDriver driver;

}
