package com.ui.users;

import client.AdministrationClient;
import com.config.Config;
import com.ui.BaseTests;
import com.utils.RandomUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import steps.UsersSteps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Test
public class CreateUserPerProjectPermissionDisabledTest extends BaseTests {
    String userName;

    @BeforeMethod
    public void login() {
        loginSteps.login(Config.getAdminUser());
        new AdministrationClient().changePerProjectPermission(false);
    }

    @Test(dataProvider = "createDisabledPermissionsUserDataProvider")
    public void createUserWithAdministrationRole(boolean isAdmin, String role) {
        userName = RandomUtil.generateRandomName();
        projectsSteps
                .getTopHeader()
                .clickOnAdministrationButton()
                .getAdministrationSideBar()
                .clickOnUsersSidebarItem()
                .createUser(userName, "1", isAdmin)
                .verifyUserExistence(userName, true)
                .verifyUserRole(userName, role, false);
    }

    @DataProvider
    public Iterator<Object[]> createDisabledPermissionsUserDataProvider(){
        Collection<Object[]> dp = new ArrayList<>();
        dp.add(new Object[]{true, "Admin"});
        dp.add(new Object[]{false, " "});
        return dp.iterator();
    }

    @AfterMethod
    void deleteUser() {
        new UsersSteps()
                .resetSearchFilter()
                .deleteUser(userName)
                .verifyUserExistence(userName, false);
    }
}
