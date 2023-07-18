import client.AdministrationClient;
import com.utils.RandomUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.UsersSteps;

public class CreateUserTest extends BaseTests {
    String userName;

    @BeforeMethod
    public void login() {
        loginSteps.login("Admin", "Admin");
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
