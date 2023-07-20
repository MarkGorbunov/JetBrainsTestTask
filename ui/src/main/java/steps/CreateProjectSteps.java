package steps;

import com.codeborne.selenide.SelenideElement;
import elements.dropdown.RingPopUp;
import elements.modal.DuplicateVCSRootModal;
import pages.CreateProjectPage;

import static com.codeborne.selenide.Condition.*;

public class CreateProjectSteps extends BaseSteps {
    private CreateProjectPage createProjectPage = new CreateProjectPage();

    public RepositoryTab selectFromRepositoryTab() {
        clickOnTab(createProjectPage.getFromRepositoryTab());
        return new RepositoryTab();
    }

    public ManuallyTab selectManuallyTab() {
        clickOnTab(createProjectPage.getManuallyTab());
        return new ManuallyTab();
    }

    private void clickOnTab(SelenideElement tab) {
        tab.click();
        tab.shouldBe(attribute("class", "createOption readyToUseOption expanded"));
    }

    public class RepositoryTab {
        public RepositoryTab selectParentProject(String parentProjectName) {
            if (createProjectPage.getParentProjectButton().isDisplayed()) { //checks if you have only 1 project
                createProjectPage.getParentProjectButton().click();
                new RingPopUp().selectOptionFromDropdown(parentProjectName);
                createProjectPage.getParentProjectButton().should(text(parentProjectName));
            }
            return this;
        }

        public RepositoryTab enterRepositoryUrl(String url) {
            createProjectPage.getRepositoryUrlInput().setValue(url);
            return this;
        }

        public CreateProjectFromUrl clickOnProceedButton() {
            createProjectPage.getNextButton().click();
            return new CreateProjectFromUrl();
        }
    }

    public class ManuallyTab {
        public ManuallyTab selectParentProject(String parentProjectName) {
            createProjectPage.getParentProjectButton().click();
            new RingPopUp().selectOptionFromDropdown(parentProjectName);
            createProjectPage.getParentProjectButton().should(text(parentProjectName));
            return this;
        }

        public ManuallyTab enterName(String name) {
            createProjectPage.getNameInput().setValue(name);
            return this;
        }

        public ManuallyTab enterProjectId(String id) {
            createProjectPage.getProjectIdInput().setValue(id);
            return this;
        }

        public ProjectSteps clickOnCreateButton() {
            createProjectPage.getNextButton().click();
            return new ProjectSteps();
        }
    }

    public class CreateProjectFromUrl {
        public CreateProjectFromUrl enterProjectName(String name) {
            createProjectPage.getProjectNameInput().clear();
            createProjectPage.getProjectNameInput().setValue(name);
            return this;
        }

        public CreateProjectFromUrl enterBuildName(String name) {
            createProjectPage.getBuildNameInput().clear();
            createProjectPage.getBuildNameInput().setValue(name);
            return this;
        }

        public CreateProjectFromUrl enterDefaultBranch(String branchPath) {
            createProjectPage.getDefaultBranchInput().clear();
            createProjectPage.getDefaultBranchInput().setValue(branchPath);
            return this;
        }

        public BuildSteps clickOnProceedButton() {
            createProjectPage.getNextButton().click();
            createProjectPage.getNextButton().should(hidden);
            return new BuildSteps();
        }

        public DuplicateVCSRootModal clickOnProceedButton(Boolean vcsRepeated) {
            createProjectPage.getNextButton().click();
            return new DuplicateVCSRootModal();
        }
    }
}
