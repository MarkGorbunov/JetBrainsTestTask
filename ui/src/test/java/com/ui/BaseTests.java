package com.ui;

import com.config.Config;
import com.codeborne.selenide.Selenide;
import config.manager.DriverManager;
import org.testng.annotations.*;
import steps.LoginSteps;
import steps.ProjectsSteps;

public class BaseTests {
    String baseUrl;
    LoginSteps loginSteps = new LoginSteps();
    ProjectsSteps projectsSteps = new ProjectsSteps();

    @BeforeSuite
    protected void setupProperties() {
        DriverManager.setupBrowser();
        baseUrl = Config.getEnv().getProperty("loginUrl");
    }

    @BeforeMethod
    protected void startUp() {
        Selenide.open(baseUrl);
    }

    @AfterMethod
    protected void shutDown() {
        Selenide.closeWebDriver();
    }
}
