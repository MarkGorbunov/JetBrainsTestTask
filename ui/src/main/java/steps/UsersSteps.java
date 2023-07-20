package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.models.Role;
import com.models.RolesModel;
import com.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.administration.UsersPage;

import java.time.Duration;

public class UsersSteps extends AdministrationSteps {
    private static final Logger Log = LoggerFactory.getLogger(UsersSteps.class);

    UsersPage usersPage = new UsersPage();

    public UsersSteps createUser(String username, String password, boolean isAdmin) {
        Log.info("Create user= " + username);
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

    public UsersSteps createUser(String username, String password) {
        return createUser(username, password, false);
    }

    public UsersSteps createUser(User user) {
        return createUser(user.getUsername(), user.getPassword(), user.isAdmin());
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

    public UsersSteps verifyUserExistence(User user, boolean exist) {
        return verifyUserExistence(user.getUsername(), exist);
    }

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

    public UsersSteps verifyUserRole(String username, String role, boolean isPerProjectPermissions) {
        Log.info("Verify user=" + username + ", have role=" + role);
        if (isPerProjectPermissions) {
            usersPage.getRowsRoles().get(countRowNumberByUsername(username) - 1).
                    $x(".//span[@class='pc__toggle-wrapper']").click();
            usersPage.getAssignedRoles().find(Condition.text(role)).should(Condition.exist);
        } else {
            usersPage.getRowsRoles().get(countRowNumberByUsername(username) - 1).should(Condition.text(role));
        }
        return this;
    }

    public UsersSteps verifyUserRole(User user, boolean isPerProjectPermissions) {
        for (Role role : user.getRoles().getRole()) {
            verifyUserRole(user.getUsername(), role.getRoleId().getRoleText(), isPerProjectPermissions);
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

    public RolesSteps navigateToUserRolesByUsername(User user) {
        return navigateToUserRolesByUsername(user.getUsername());
    }

    public UsersSteps deleteUser(String username) {
        Log.info("Delete user= " + username);
        usersPage.getSearchInput().clear();
        usersPage.getSearchInput().setValue(username);
        usersPage.getFilterButton().click();
        clickOnCheckboxByUsername(username);
        usersPage.getRemoveUsersButton().click();
        Selenide.switchTo().alert(Duration.ofSeconds(3)).accept();
        return this;
    }

    public UsersSteps deleteUser(User user) {
        return deleteUser(user.getUsername());
    }

    private int countRowNumberByUsername(String username) {
        SelenideElement row = usersPage.getRowsUsernames().find(Condition.innerText(username));
        return usersPage.getRowsUsernames().indexOf(row) + 1;
    }
}
