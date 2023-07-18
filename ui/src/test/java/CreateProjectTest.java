import com.config.Config;
import com.utils.RandomUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateProjectTest extends BaseTests {
    String projectName;

    @BeforeMethod
    public void login() {
        loginSteps.login(Config.getAdminUser());
        projectName = RandomUtil.generateRandomName();
    }

    @Test
    public void createProjectFromUrl() {
        projectsSteps.getTopHeader()
                .clickOnAddProjectButton()
                .selectFromRepositoryTab()
                .selectParentProject("Common Project")
                .enterRepositoryUrl("https://github.com/MarkGorbunov/SimpleTests.git")
                .clickOnProceedButton()
                .enterProjectName(projectName)
                .enterBuildName(projectName)
                .clickOnProceedButton(true)
                .verifyModalName("Duplicate VCS Roots Found")
                .clickOnCreateDuplicateVCSRootButton();

        //cleanup
        projectsSteps.getTopHeader()
                .clickOnProjects()
                .performProjectSearch(projectName)
                .clickOnEditProjectButton()
                .clickOnDeleteProject()
                .verifyModalName("Delete " + "\"" + projectName + "\"" + " Project")
                .enterConfirmationCode("localhost:8112")
                .clickOnDeleteButton();
    }

    @Test()
    public void createProjectManually() {
        projectsSteps.getTopHeader()
                .clickOnAddProjectButton()
                .selectManuallyTab()
                .selectParentProject("Common Project")
                .enterName(projectName)
                .enterProjectId(RandomUtil.generateRandomName())
                .clickOnCreateButton();

        //cleanup
        projectsSteps.getTopHeader()
                .clickOnProjects()
                .performProjectSearch(projectName)
                .clickOnEditProjectButton()
                .clickOnDeleteProject();
    }
}
