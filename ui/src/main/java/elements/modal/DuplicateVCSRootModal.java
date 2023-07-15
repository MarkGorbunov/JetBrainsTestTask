package elements.modal;

import com.codeborne.selenide.Condition;
import steps.BuildSteps;

public class DuplicateVCSRootModal extends BaseModal<DuplicateVCSRootModal> {
    public DuplicateVCSRootModal() {
        super(DuplicateVCSRootModal::new);
    }

    public BuildSteps clickOnCreateDuplicateVCSRootButton() {
        primaryButton.click();
        primaryButton.should(Condition.hidden);
        return new BuildSteps();
    }
}
