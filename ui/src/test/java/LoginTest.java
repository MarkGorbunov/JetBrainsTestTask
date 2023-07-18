import com.codeborne.selenide.Selenide;
import com.config.Config;
import org.testng.annotations.Test;
import steps.LoginSteps;

public class LoginTest extends BaseTests {

    @Test
    public void initialTest() {
        Selenide.open("http://localhost:8112/login.html");
        loginSteps.login(Config.getAdminUser());
    }
}
