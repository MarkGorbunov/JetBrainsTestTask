package steps;

import com.codeborne.selenide.Condition;
import elements.modal.PopUp;
import pages.ProjectPage;

public class ProjectSteps extends BaseSteps {
    ProjectPage projectPage = new ProjectPage();

    public EditProjectSteps clickOnEditProjectButton() {
        projectPage.getEditProject().click();
        projectPage.getEditProject().should(Condition.hidden);
        return new EditProjectSteps();
    }

    public ProjectSteps clickOnRunBuildButton() {
        projectPage.getRunBuildButton().click();
        new PopUp().verifyPopUpTextMatch("The build #\\d+ is running");
        return this;
    }

    public ProjectSteps waitBuildFinished() {
        new PopUp().verifyPopUpTextMatch("The build #\\d+ is finished");
        return this;
    }

    public ProjectSteps expandBuildByName(String buildName) {
        if (!projectPage.getBuildStatus().isDisplayed()) {
            projectPage.getBuilds().find(Condition.attribute("aria-label", buildName)).click();
        }
        return this;
    }

    public ProjectSteps verifyBuildStatus(String status) {
        projectPage.getBuildStatus().should(Condition.text(status));
        return this;
    }
}
