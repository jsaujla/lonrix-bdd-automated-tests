package com.lonrix.qa.steps;

import com.lonrix.qa.common.PropertiesManager;
import com.lonrix.qa.common.WebDriverManager;
import com.lonrix.qa.pages.JunoViewerDashboardHeader;
import com.lonrix.qa.pages.JunoViewerLoginPage;
import com.lonrix.qa.pages.JunoViewerStripCustomViewPage;

/**
 * <h1>Page Initializer</h1>
 * This class is used to initialize page object classes and provide the page objects to step classes.
 * <p>
 * @author Jaspal Aujla
 */
public class PageInitializer {

    //********** OBJECT DECLARATION **********
    protected DependencyContainer dependencyContainer;
    protected WebDriverManager webDriverManager;
    protected PropertiesManager propertiesManager;

    private JunoViewerLoginPage junoViewerLoginPage;
    private JunoViewerDashboardHeader junoViewerDashboardHeader;
    private JunoViewerStripCustomViewPage junoViewerStripCustomViewPage;

    /**
     * Parameterized Constructor
     *
     * @param dependencyContainer
     */
    public PageInitializer(DependencyContainer dependencyContainer) {
        this.dependencyContainer = dependencyContainer;
        this.webDriverManager = dependencyContainer.webDriverManager;
        this.propertiesManager = dependencyContainer.propertiesManager;
    }

    protected JunoViewerLoginPage getSigninPage() {
        if(junoViewerLoginPage == null) {
            return junoViewerLoginPage = new JunoViewerLoginPage(webDriverManager);
        }
        return junoViewerLoginPage;
    }

    protected JunoViewerDashboardHeader getJunoViewerDashboardHeader() {
        if(junoViewerDashboardHeader == null) {
            return junoViewerDashboardHeader = new JunoViewerDashboardHeader(webDriverManager);
        } else {
            return junoViewerDashboardHeader;
        }
    }

    protected JunoViewerStripCustomViewPage getStripCustomViewPage() {
        if(junoViewerStripCustomViewPage == null) {
            return junoViewerStripCustomViewPage = new JunoViewerStripCustomViewPage(webDriverManager);
        } else {
            return junoViewerStripCustomViewPage;
        }
    }
}
