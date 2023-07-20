package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ProjectPage {
    private SelenideElement editProject = Selenide.$x("//div[@data-hint-container-id='edit-entity']");
    private SelenideElement runBuildButton = Selenide.$x("//button[@data-test='run-build']");
    private SelenideElement buildStatus = Selenide.$x("//div[contains(@class, 'Build__status')]/a");

    private ElementsCollection builds = Selenide.$$x("//button[contains(@class, 'Details')]");
}
