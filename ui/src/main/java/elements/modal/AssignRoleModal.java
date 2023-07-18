package elements.modal;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import steps.RolesSteps;

import static com.codeborne.selenide.Selenide.$$x;

public class AssignRoleModal extends BaseModal<AssignRoleModal> {
    private final SelenideElement rolesInput = baseElement.$x(".//input[@id='-ufd-teamcity-ui-assignRole_role']");
    private final ElementsCollection rolesDropDown = $$x("//div[@class='list-wrapper list-wrapper-ufd-teamcity-ui-role']//ul//li");
    private final ElementsCollection projectsRows = baseElement.$$x(".//div[@aria-label]//div[@role='row']");

    public AssignRoleModal() {
        super(AssignRoleModal::new);
    }

    public AssignRoleModal selectRole(String role) {
        rolesInput.click();
        rolesDropDown.find(Condition.text(role)).click();
        return this;
    }

    public AssignRoleModal selectProject(String project) {
        projectsRows.find(Condition.text(project)).$x(".//input[@class='ring-checkbox-input']").click();
        return this;
    }

    public RolesSteps clickOnAssignProjectButton() {
        primaryButton.click();
        primaryButton.should(Condition.hidden);
        return new RolesSteps();
    }
}
