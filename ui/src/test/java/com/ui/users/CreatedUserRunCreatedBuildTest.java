package com.ui.users;

import client.AdministrationClient;
import com.config.Config;
import com.ui.BaseTests;
import com.utils.RandomUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class CreatedUserRunCreatedBuildTest extends BaseTests {
    private String userName;
    private String projectName;
    private String repositoryUrl = "https://github.com/MarkGorbunov/SimpleTests.git";
    private String branch = "refs/heads/passed_tests";
    private String stepName = "Gradle";

    @BeforeMethod
    public void setUp() {
        loginSteps.login(Config.getAdminUser());
        projectName = RandomUtil.generateRandomName();
    }

    @Test
    public void createUserWithAdministrationRole() {
        userName = RandomUtil.generateRandomName();
        projectsSteps.getTopHeader()
                .clickOnAddProjectButton()
                .selectFromRepositoryTab()
                .selectParentProject("<Root project>")
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
