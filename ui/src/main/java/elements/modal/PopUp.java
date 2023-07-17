package elements.modal;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class PopUp {
    private SelenideElement popUp= $x("//div[contains(@data-portaltarget, 'popup')]");

    public void verifyPopUpText(String text) {
        popUp.should(Condition.text(text));
    }

    public void verifyPopUpTextMatch(String text) {
        popUp.should(Condition.matchText(text), Duration.ofSeconds(30));
    }
}
