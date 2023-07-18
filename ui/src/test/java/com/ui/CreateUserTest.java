package com.ui;

import client.AdministrationClient;
import com.codeborne.selenide.testng.TextReport;
import com.config.Config;
import com.utils.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import steps.UsersSteps;

@Listeners({ TextReport.class})
public class CreateUserTest extends BaseTests {
    private static final Logger Log = LoggerFactory.getLogger(CreateUserTest .class);

    String userName;

    @BeforeMethod
    public void login() {
        loginSteps.login(Config.getAdminUser());
    }

    @Test
    public void createUser() {
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
//                .verifyRoleByUsername(userName, "Admin", true);

        //delete user
        new UsersSteps()
                .resetSearchFilter()
                .deleteUserByUsername(userName)
                .verifyUserExistence(userName, false);
    }

    @Test
    public void createUserWithAdministrationRole() {
        new AdministrationClient().changePerProjectPermission(false);
        Log.info("<<<<<<<<verytest>>>>>>>>>>>>");
        userName = RandomUtil.generateRandomName();
        projectsSteps
                .getTopHeader()
                .clickOnAdministrationButton()
                .getAdministrationSideBar()
                .clickOnUsersSidebarItem()
                .createUser(userName, "1", true)
                .verifyUserExistence(userName, true)
                .verifyRoleByUsername(userName, "Admin", false);

        //delete user
        new UsersSteps()
                .resetSearchFilter()
                .deleteUserByUsername(userName)
                .verifyUserExistence(userName, false);
    }
}
