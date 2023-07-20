package com.ui.users;

import client.AdministrationClient;
import com.config.Config;
import com.models.Role;
import com.models.Roles;
import com.models.RolesModel;
import com.models.User;
import com.ui.BaseTests;
import com.utils.RandomUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.UsersSteps;

import java.util.List;

@Test
public class CreateUserPerProjectPermissionEnabledTest extends BaseTests {
    User user;

    @BeforeMethod
    public void setUp() {
        loginSteps.login(Config.getAdminUser());

        user = User.builder()
                .username(RandomUtil.generateRandomName())
                .password(RandomUtil.generateRandomNumbers(5))
                .roles(new Roles(List.of(new Role(RolesModel.PROJECT_ADMIN, "g"))))
                .isAdmin(false)
                .build();
    }

    @Test
    public void createUserWhenPerProjectPermissionEnabled() {
        projectsSteps
                .getTopHeader()
                .clickOnAdministrationButton()
                .getAdministrationSideBar()
                .clickOnUsersSidebarItem()
                .createUser(user)
                .verifyUserExistence(user, true)
                .navigateToUserRolesByUsername(user)
                .assignProject(user.getRoles().getRole().get(0).getRoleId().getRoleValue(), "<Root project>")
                .verifySuccessMessage("Roles updated.")
                .getTopHeader()
                .clickOnAdministrationButton()
                .getAdministrationSideBar()
                .clickOnUsersSidebarItem()
                .verifyUserExistence(user, true)
                .verifyUserRole(user, true);
    }

    @AfterMethod
    void deleteUser() {
        new UsersSteps()
                .resetSearchFilter()
                .deleteUser(user)
                .verifyUserExistence(user, false);
    }
}
