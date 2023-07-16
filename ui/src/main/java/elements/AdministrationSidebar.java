package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import steps.UsersSteps;

import static model.AdministrationSidebarOptions.USERS;


public class AdministrationSidebar {
    private ElementsCollection sidebarOptions = Selenide.$$x("//td[@class='admin-sidebar']//div[@class='item']");

    public UsersSteps clickOnUsersSidebarItem() {
        sidebarOptions.find(Condition.text(USERS.getText())).click();
        return new UsersSteps();
    }
}
