package config.manager;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.PageLoadStrategy;

public class DriverManager {

    public static void setupBrowser() {
        Configuration.browser = System.getProperty("browser").isEmpty() ? "chrome" : System.getProperty("browser");
        Configuration.timeout = 15000;
        Configuration.browserSize = "1366x768";
        Configuration.proxyEnabled = false;
        Configuration.pageLoadStrategy = PageLoadStrategy.NONE.toString();
    }
}
