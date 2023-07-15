package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class EditProjectPage {
    private SelenideElement actionsButton = Selenide.$x("//div[@data-hint-container-id='project-admin-actions']");

}
