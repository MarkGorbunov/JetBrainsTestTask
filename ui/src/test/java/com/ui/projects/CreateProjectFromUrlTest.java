package com.ui.projects;

import com.config.Config;
import com.ui.BaseTests;
import com.utils.RandomUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class CreateProjectFromUrlTest extends BaseTests {
    private String projectName;

    @BeforeMethod
    public void setUp() {
        loginSteps.login(Config.getAdminUser());
        projectName = RandomUtil.generateRandomName();
    }

    @Test
    public void createProjectFromUrl() {
        projectsSteps.getTopHeader()
                .clickOnAddProjectButton()
                .selectFromRepositoryTab()
                .selectParentProject("<Root project>")
                .enterRepositoryUrl("https://github.com/MarkGorbunov/SimpleTests.git")
                .clickOnProceedButton()
                .enterProjectName(projectName)
                .enterBuildName(projectName)
                .clickOnProceedButton()
                .clickOnCheckboxByStepName("Gradle")
                .clickOnUseSelectedButton()
                .verifySuccessMessage("New build step added.");
    }

    @AfterMethod
    void deleteProject() {
        projectsSteps.getTopHeader()
                .clickOnProjects()
                .performProjectSearch(projectName)
                .clickOnEditProjectButton()
                .clickOnDeleteProject()
                .verifyModalName("Delete " + "\"" + projectName + "\"" + " Project")
                .enterConfirmationCode(confirmationCode)
                .clickOnDeleteButton();
    }
}
