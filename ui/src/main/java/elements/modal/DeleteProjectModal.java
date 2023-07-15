package elements.modal;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import steps.ProjectSteps;

public class DeleteProjectModal extends BaseModal<DeleteProjectModal> {
    private SelenideElement confirmationInput = Selenide.$x("//input[@id='deleteProject__hostnameConfirmation']");
    public DeleteProjectModal() {
        super(DeleteProjectModal::new);
    }

    public DeleteProjectModal enterConfirmationCode(String code) {
        confirmationInput.setValue(code);
        return this;
    }

    public ProjectSteps clickOnDeleteButton() {
        primaryButton.shouldNot(Condition.attribute("disabled")).click();
        return new ProjectSteps();
    }
}
