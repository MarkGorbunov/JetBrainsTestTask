package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.BuildPage;

public class BuildSteps extends BaseSteps {
    private BuildPage buildPage = new BuildPage();

    public BuildSteps clickOnCheckboxByStepName(String stepName) {
        buildPage.getRunnersCheckbox().get(countRowNumberByStepName(stepName)).click();
        return this;
    }

    public BuildSteps clickOnUseSelectedButton() {
        buildPage.getUseSelectedButton().click();
        return this;
    }

    public BuildSteps verifySuccessMessage(String successMessage) {
        buildPage.getSuccessMessage().should(Condition.text(successMessage));
        return this;
    }

    private int countRowNumberByStepName(String stepName) {
        SelenideElement row = buildPage.getRunnersSteps().find(Condition.innerText(stepName));
        return buildPage.getRunnersSteps().indexOf(row) + 1;
    }
}
