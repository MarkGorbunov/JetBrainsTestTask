package steps;

import com.codeborne.selenide.Condition;
import pages.ProjectPage;

public class ProjectSteps extends BaseSteps {
    ProjectPage projectPage = new ProjectPage();

    public EditProjectSteps clickOnEditProjectButton() {
        projectPage.getEditProject().click();
        projectPage.getEditProject().should(Condition.hidden);
        return new EditProjectSteps();
    }
}
