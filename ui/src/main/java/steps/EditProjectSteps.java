package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import elements.dropdown.MenuListItems;
import elements.modal.DeleteProjectModal;
import pages.EditProjectPage;

public class EditProjectSteps extends BaseSteps {
    EditProjectPage editProjectPage = new EditProjectPage();

    public DeleteProjectModal clickOnDeleteProject() {
        editProjectPage.getActionsButton().should(Condition.visible);
        Selenide.actions().moveToElement(editProjectPage.getActionsButton()).click().perform();
        new MenuListItems().selectOptionFromDropdown("Delete project...");
        return new DeleteProjectModal();
    }
}
