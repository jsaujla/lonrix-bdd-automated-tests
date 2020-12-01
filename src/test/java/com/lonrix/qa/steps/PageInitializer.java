package com.lonrix.qa.steps;

import com.lonrix.qa.common.PropertiesManager;
import com.lonrix.qa.common.WebDriverManager;
import com.lonrix.qa.pages.JunoViewerDashboardHeader;
import com.lonrix.qa.pages.JunoViewerSigninPage;
import com.lonrix.qa.pages.StripCustomViewPage;

public class PageInitializer {

    //********** OBJECT DECLARATION **********
    protected DependencyContainer dependencyContainer;
    protected WebDriverManager webDriverManager;
    protected PropertiesManager propertiesManager;

    private JunoViewerSigninPage junoViewerSigninPage;
    private JunoViewerDashboardHeader junoViewerDashboardHeader;
    private StripCustomViewPage stripCustomViewPage;

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

    protected JunoViewerSigninPage getSigninPage() {
        if(junoViewerSigninPage == null) {
            return junoViewerSigninPage = new JunoViewerSigninPage(webDriverManager);
        }
        return junoViewerSigninPage;
    }

    protected JunoViewerDashboardHeader getJunoViewerDashboardHeader() {
        if(junoViewerDashboardHeader == null) {
            return junoViewerDashboardHeader = new JunoViewerDashboardHeader(webDriverManager);
        } else {
            return junoViewerDashboardHeader;
        }
    }

    protected StripCustomViewPage getStripCustomViewPage() {
        if(stripCustomViewPage == null) {
            return stripCustomViewPage = new StripCustomViewPage(webDriverManager);
        } else {
            return stripCustomViewPage;
        }
    }
}
