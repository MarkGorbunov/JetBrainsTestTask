package com.ui.builds;

import com.config.Config;
import com.ui.BaseTests;
import com.utils.RandomUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Test
public class RunBuildTests extends BaseTests {
    private String projectName;

    @BeforeMethod
    public void setUp() {
        loginSteps.login(Config.getAdminUser());
        projectName = RandomUtil.generateRandomName();
    }

    @Test(dataProvider = "runBuildTestDataProvider")
    public void runBuild(String branch, String resultMessage) {
        projectsSteps.getTopHeader()
                .clickOnAddProjectButton()
                .selectFromRepositoryTab()
                .selectParentProject("<Root project>")
                .enterRepositoryUrl("https://github.com/MarkGorbunov/SimpleTests.git")
                .clickOnProceedButton()
                .enterProjectName(projectName)
                .enterBuildName(projectName)
                .enterDefaultBranch(branch)
                .clickOnProceedButton()
                .clickOnCheckboxByStepName("Gradle")
                .clickOnUseSelectedButton()
                .verifySuccessMessage("New build step added.")
                .getTopHeader().clickOnProjects()
                .performProjectSearch(projectName)
                .clickOnRunBuildButton()
                .waitBuildFinished()
                .expandBuildByName(projectName)
                .verifyBuildStatus(resultMessage);
    }

    @AfterMethod(alwaysRun = true)
    public void deleteRun() {
        projectsSteps.getTopHeader()
                .clickOnProjects()
                .performProjectSearch(projectName)
                .clickOnEditProjectButton()
                .clickOnDeleteProject()
                .verifyModalName("Delete " + "\"" + projectName + "\"" + " Project")
                .enterConfirmationCode(confirmationCode)
                .clickOnDeleteButton();
    }

    @DataProvider
    public Iterator<Object[]> runBuildTestDataProvider() {
        Collection<Object[]> dp = new ArrayList<>();
        dp.add(new Object[]{"refs/heads/failed_tests", "Tests failed: 1 (1 new), passed: 1"});
        dp.add(new Object[]{"refs/heads/passed_tests", "Tests passed: 2"});
        return dp.iterator();
    }
}
