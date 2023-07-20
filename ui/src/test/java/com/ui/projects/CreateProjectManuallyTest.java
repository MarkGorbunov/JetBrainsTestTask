package com.ui.projects;

import com.config.Config;
import com.ui.BaseTests;
import com.utils.RandomUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class CreateProjectManuallyTest extends BaseTests {

    private String projectName;

    @BeforeMethod
    public void setUp() {
        loginSteps.login(Config.getAdminUser());
        projectName = RandomUtil.generateRandomName();
    }

    @Test()
    public void createProjectManually() {
        projectsSteps.getTopHeader()
                .clickOnAddProjectButton()
                .selectManuallyTab()
                .selectParentProject("<Root project>")
                .enterName(projectName)
                .enterProjectId(RandomUtil.generateRandomName())
                .clickOnCreateButton();
    }

    @AfterMethod
    void deleteProject() {
        projectsSteps.getTopHeader()
                .clickOnProjects()
                .performProjectSearch(projectName)
                .clickOnEditProjectButton()
                .clickOnDeleteProject();
    }
}
