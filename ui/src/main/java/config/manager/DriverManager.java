package config.manager;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.logging.Level;

public class DriverManager {

    public static void setupBrowser() {
        Configuration.browser = System.getProperty("browser").isEmpty() ? "chrome" : System.getProperty("browser");
        Configuration.timeout = 15000;
        Configuration.browserSize = "1366x768";
        Configuration.proxyEnabled = false;
        Configuration.pageLoadStrategy = PageLoadStrategy.NONE.toString();
        Configuration.webdriverLogsEnabled = true;
//        LoggingPreferences logPrefs = new LoggingPreferences();
//        logPrefs.enable(LogType.BROWSER, Level.ALL);
//        Capa
//        capabilities.setCapability("goog:loggingPrefs", logPrefs);
    }
}
