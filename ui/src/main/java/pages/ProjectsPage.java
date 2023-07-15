package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ProjectsPage {
    private SelenideElement searchProjectsInput = Selenide.$x("//input[@id='search-projects']");
    private SelenideElement sidebar = Selenide.$x("//div[@data-sidebar-list]");
    private ElementsCollection sidebarOptions = sidebar.$$x(".//div[@role='row']");
}
