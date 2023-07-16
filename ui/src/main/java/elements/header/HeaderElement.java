package elements.header;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import steps.AdministrationSteps;
import steps.CreateProjectSteps;
import steps.ProjectsSteps;

public class HeaderElement {
    private SelenideElement baseElement = Selenide.$x("//div[@id='topWrapper']");
    private SelenideElement linksContainer = baseElement.$x(".//div[contains(@class,'Links__container')]");
    private SelenideElement projectsLink = linksContainer.$x(".//a[@href='/favorite/projects']");
    private SelenideElement addProjectButton = projectsLink.$x("./following-sibling::a");
    private SelenideElement changesLink = linksContainer.$x(".//a[@href='/changes']");
    private SelenideElement agentsLink = linksContainer.$x(".//a[@href='/agents']");
    private SelenideElement queueLink = linksContainer.$x(".//a[@href='/queue']");
    private SelenideElement administrationLink = baseElement.$x(".//div[@data-hint-container-id='header-administration-link']");

    public ProjectsSteps clickOnProjects() {
        projectsLink.click();
        return new ProjectsSteps();
    }

    public CreateProjectSteps clickOnAddProjectButton() {
        addProjectButton.click();
        return new CreateProjectSteps();
    }

    public AdministrationSteps clickOnAdministrationButton() {
        administrationLink.click();
        return new AdministrationSteps();
    }
}
