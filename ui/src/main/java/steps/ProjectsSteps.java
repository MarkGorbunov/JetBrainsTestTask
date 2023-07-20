package steps;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import pages.ProjectsPage;

public class ProjectsSteps extends BaseSteps {
    private ProjectsPage projectsPage = new ProjectsPage();

    public ProjectSteps performProjectSearch(String projectName) {
        projectsPage.getSearchProjectsInput().clear();
        projectsPage.getSearchProjectsInput().setValue(projectName);
        projectsPage.getSearchProjectsInput().pressEnter();
        waitPageToLoad();
        if (projectsPage.getAddFavoriteCloseButton().isDisplayed()) {
            projectsPage.getAddFavoriteCloseButton().click();
        }
        projectsPage.getSidebarOptions().shouldBe(CollectionCondition.sizeGreaterThan(0))
                .find(Condition.text(projectName)).click();
        return new ProjectSteps();
    }
}
