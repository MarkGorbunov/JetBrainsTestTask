package steps;

import com.codeborne.selenide.Condition;
import elements.menulist.MenuListItems;
import elements.modal.DeleteProjectModal;
import pages.EditProjectPage;

public class EditProjectSteps extends BaseSteps {
    EditProjectPage editProjectPage = new EditProjectPage();

    public DeleteProjectModal clickOnDeleteProject() {
        editProjectPage.getActionsButton().click();
        new MenuListItems().selectOptionFromDropdown("Delete project...");
        return new DeleteProjectModal();
    }
}
