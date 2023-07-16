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
        userName = RandomUtil.generateRandomName();
        projectsSteps
                .getTopHeader()
                .clickOnAdministrationButton()
                .getAdministrationSideBar()
                .clickOnUsersSidebarItem()
                .createUser(userName, "1")
                .verifyUserExistence(userName, true);

        //delete user
        new UsersSteps()
                .resetSearchFilter()
                .deleteUserByUsername(userName)
                .verifyUserExistence(userName, false);
    }
}
