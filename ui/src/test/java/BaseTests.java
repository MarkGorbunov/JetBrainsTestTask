import com.Config;
import com.codeborne.selenide.Selenide;
import config.manager.DriverManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseTests {
    String baseUrl;

    @BeforeSuite
    protected void setupProperties() {
        DriverManager.setupBrowser();
        baseUrl = Config.getEnv().getProperty("loginUrl");
    }

    @BeforeTest
    protected void startUp() {
        Selenide.open(baseUrl);
    }

    @AfterTest
    protected void shutDown() {
        Selenide.closeWebDriver();
    }
}
