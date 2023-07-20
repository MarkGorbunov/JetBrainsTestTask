package utils;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public interface PageReloadable {
    default void waitPageToLoad() {
        Selenide.Wait().withTimeout(Duration.ofSeconds(30)).until(webDriver -> ((JavascriptExecutor) webDriver).
                executeScript("return document.readyState").equals("complete"));
        Selenide.Wait().withTimeout(Duration.ofSeconds(30)).until(webDriver -> ((JavascriptExecutor) webDriver).
                executeScript("return jQuery.active").equals(0L));
    }

}
