package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import elements.modal.AssignRoleModal;
import pages.RolesPage;

import static com.codeborne.selenide.Selenide.$x;

public class RolesSteps extends BaseSteps{
    RolesPage rolesPage = new RolesPage();
    public RolesSteps assignProject(String role, String project) {
        rolesPage.getAssignRoleButton().click();
        new AssignRoleModal().selectRole(role)
                .selectProject(project).clickOnAssignProjectButton();
        return this;
    }

    public RolesSteps verifySuccessMessage(String message) {
        rolesPage.getSuccessMessage().should(Condition.text(message));
        return this;
    }


}
