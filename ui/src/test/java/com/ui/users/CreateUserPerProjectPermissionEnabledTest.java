package com.ui.users;

import client.AdministrationClient;
import com.config.Config;
import com.ui.BaseTests;
import com.utils.RandomUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.UsersSteps;

@Test
public class CreateUserPerProjectPermissionEnabledTest extends BaseTests {
    String userName;

    @BeforeMethod
    public void login() {
        loginSteps.login(Config.getAdminUser());
    }

    public void createUserWhenPerProjectPermissionEnabled() {
        new AdministrationClient().changePerProjectPermission(true);
        userName = RandomUtil.generateRandomName();
        projectsSteps
                .getTopHeader()
                .clickOnAdministrationButton()
                .getAdministrationSideBar()
                .clickOnUsersSidebarItem()
                .createUser(userName, "1")
                .verifyUserExistence(userName, true)
                .navigateToUserRolesByUsername(userName)
                .assignProject("Project administrator", "<Root project>")
                .verifySuccessMessage("Roles updated.")
                .getTopHeader()
                .clickOnAdministrationButton()
                .getAdministrationSideBar()
                .clickOnUsersSidebarItem()
                .verifyUserExistence(userName, true)
                .verifyRoleByUsername(userName, "Project administrator", true);
    }

    @AfterMethod
    void deleteUser() {
        new UsersSteps()
                .resetSearchFilter()
                .deleteUserByUsername(userName)
                .verifyUserExistence(userName, false);
    }
}
