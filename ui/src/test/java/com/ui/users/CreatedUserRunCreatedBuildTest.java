package com.ui.users;

import client.AdministrationClient;
import com.config.Config;
import com.ui.BaseTests;
import com.utils.RandomUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreatedUserRunCreatedBuildTest extends BaseTests {
    String userName;
    String projectName;
    String repositoryUrl = "https://github.com/MarkGorbunov/SimpleTests.git";
    String branch = "refs/heads/passed_tests";
    String stepName = "Gradle";

    @BeforeMethod
    public void login() {
        loginSteps.login(Config.getAdminUser());
        new AdministrationClient().changePerProjectPermission(false);
        projectName = RandomUtil.generateRandomName();
    }

    @Test
    public void createUserWithAdministrationRole() {
        userName = RandomUtil.generateRandomName();
        projectsSteps.getTopHeader()
                .clickOnAddProjectButton()
                .selectFromRepositoryTab()
                .selectParentProject("Common Project")
                .enterRepositoryUrl(repositoryUrl)
                .clickOnProceedButton()
                .enterProjectName(projectName)
                .enterBuildName(projectName)
                .enterDefaultBranch(branch)
                .clickOnProceedButton()
                .clickOnCheckboxByStepName(stepName)
                .clickOnUseSelectedButton()
                .verifySuccessMessage("New build step added.")
                .getTopHeader()
                .clickOnAdministrationButton()
                .getAdministrationSideBar()
                .clickOnUsersSidebarItem()
                .createUser(userName, "1", false)
                .verifyUserExistence(userName, true)
                .verifyUserRole(userName, " ", false)
                .getTopHeader()
                .logout()
                .login(userName, "1")
                .getTopHeader()
                .clickOnProjects()
                .performProjectSearch(projectName)
                .clickOnRunBuildButton()
                .waitBuildFinished()
                .expandBuildByName(projectName)
                .verifyBuildStatus("Tests passed: 2");
    }
}
