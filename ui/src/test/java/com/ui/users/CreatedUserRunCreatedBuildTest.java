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
                .enterRepositoryUrl("https://github.com/MarkGorbunov/SimpleTests.git")
                .clickOnProceedButton()
                .enterProjectName(projectName)
                .enterBuildName(projectName)
                .enterDefaultBranch("refs/heads/passed_tests")
                .clickOnProceedButton()
                .clickOnCheckboxByStepName("Gradle")
                .clickOnUseSelectedButton()
                .verifySuccessMessage("New build step added.")
                .getTopHeader()
                .clickOnAdministrationButton()
                .getAdministrationSideBar()
                .clickOnUsersSidebarItem()
                .createUser(userName, "1", false)
                .verifyUserExistence(userName, true)
                .verifyRoleByUsername(userName, " ", false)
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
