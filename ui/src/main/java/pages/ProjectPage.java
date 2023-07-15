package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ProjectPage {
    private SelenideElement editProject = Selenide.$x("//div[@data-hint-container-id='edit-entity']");
}
