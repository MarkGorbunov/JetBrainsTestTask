package com.ui;

import com.codeborne.selenide.testng.TextReport;
import com.config.Config;
import com.codeborne.selenide.Selenide;
import config.manager.DriverManager;
import org.testng.annotations.*;
import steps.LoginSteps;
import steps.ProjectsSteps;

@Listeners({ TextReport.class})
public class BaseTests {
    protected String baseUrl;
    protected LoginSteps loginSteps = new LoginSteps();
    protected ProjectsSteps projectsSteps = new ProjectsSteps();

    @BeforeTest(alwaysRun = true)
    protected void setupProperties() {
        DriverManager.setupBrowser();
        baseUrl = Config.getEnv().getProperty("baseUrl");
    }

    @BeforeMethod(alwaysRun = true)
    protected void openLoginPage() {
        Selenide.open(baseUrl + "/login.html");
    }

    @AfterMethod(alwaysRun = true)
    protected void shutDown() {
        Selenide.closeWebDriver();
    }
}
