package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.administration.UsersPage;

import java.time.Duration;

public class UsersSteps extends AdministrationSteps {
    private static final Logger Log = LoggerFactory.getLogger(UsersSteps .class);

    UsersPage usersPage = new UsersPage();

    public UsersSteps createUser(String username, String password) {
        Log.info("Create user= " + username);

        usersPage.getCreateUserLink().click();
        usersPage.getUsername().setValue(username);
        usersPage.getPassword().setValue(password);
        usersPage.getConfirmPassword().setValue(password);
        usersPage.getCreateUserButton().click();
        return this;
    }

    public UsersSteps createUser(String username, String password, boolean isAdmin) {
        usersPage.getCreateUserLink().click();

        usersPage.getUsername().setValue(username);
        usersPage.getPassword().setValue(password);
        usersPage.getConfirmPassword().setValue(password);
        if (isAdmin) {
            usersPage.getAdminPermissionsCheckbox().click();
        }
        usersPage.getCreateUserButton().click();
        return this;
    }


    public UserSteps navigateToUser(String username) {
        usersPage.getSearchInput().setValue(username);
        usersPage.getFilterButton().click();
        usersPage.getRowsUsernames().find(Condition.innerText(username)).click();
        return new UserSteps();
    }

    public UsersSteps verifyUserExistence(String username, boolean exist) {
        usersPage.getSearchInput().clear();
        usersPage.getSearchInput().setValue(username);
        usersPage.getFilterButton().click();
        if (exist) {
            usersPage.getRowsUsernames().find(Condition.innerText(username))
                    .should(Condition.visible, Duration.ofSeconds(5));
        } else {
            usersPage.getRowsUsernames().find(Condition.innerText(username))
                    .should(Condition.not(Condition.exist), Duration.ofSeconds(3));
        }
        return this;
    }

    @SneakyThrows
    public UsersSteps resetSearchFilter() {
        if (usersPage.getResetSearch().isDisplayed()) {
            usersPage.getResetSearch().click();
            usersPage.getResetSearch().shouldBe(Condition.hidden);
        }
        return this;
    }

    public UsersSteps clickOnCheckboxByUsername(String username) {
        usersPage.getRowsCheckboxes().get(countRowNumberByUsername(username)).click();
        return this;
    }

    public UsersSteps verifyRoleByUsername(String username, String role, boolean isPerProjectPermissions) {
        Log.info("Verify user=" + username + ", have role=" + role);
        if (isPerProjectPermissions) {
            usersPage.getRowsRoles().get(countRowNumberByUsername(username) - 1).
                    $x(".//span[@class='pc__toggle-wrapper']").click();
            usersPage.getAssignedRole().should(Condition.text(role));
        } else {
            usersPage.getRowsRoles().get(countRowNumberByUsername(username) - 1).should(Condition.text(role));
        }
        return this;
    }

    public RolesSteps navigateToUserRolesByUsername(String username) {
        Log.info("Navigate to user= " + username + " roles");
        SelenideElement elem = usersPage.getRowsRoles().get(countRowNumberByUsername(username) - 1);
        elem.click();
        elem.should(Condition.hidden);

        return new RolesSteps();
    }

    public UsersSteps deleteUserByUsername(String username) {
        Log.info("Delete user= " + username);
        usersPage.getSearchInput().clear();
        usersPage.getSearchInput().setValue(username);
        usersPage.getFilterButton().click();
        clickOnCheckboxByUsername(username);
        usersPage.getRemoveUsersButton().click();
        Selenide.switchTo().alert(Duration.ofSeconds(3)).accept();
        return this;
    }

    private int countRowNumberByUsername(String username) {
        SelenideElement row = usersPage.getRowsUsernames().find(Condition.innerText(username));
        return usersPage.getRowsUsernames().indexOf(row) + 1;
    }
}
