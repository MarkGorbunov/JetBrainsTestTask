package steps;

import com.codeborne.selenide.Condition;
import pages.ProjectsPage;

public class ProjectsSteps extends BaseSteps {
    ProjectsPage projectsPage = new ProjectsPage();

    public ProjectSteps performProjectSearch(String projectName) {
        projectsPage.getSearchProjectsInput().clear();
        projectsPage.getSearchProjectsInput().setValue(projectName);
        projectsPage.getSearchProjectsInput().pressEnter();
        if (projectsPage.getAddFavoriteCloseButton().isDisplayed()) {
            projectsPage.getAddFavoriteCloseButton().click();
        }
        projectsPage.getSidebarOptions().find(Condition.text(projectName)).click();
        return new ProjectSteps();
    }
}
