package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class LoginPage {
    private final SelenideElement usernameInput = Selenide.$x("//input[@id='username']");
    private final SelenideElement passwordInput = Selenide.$x("//input[@id='password']");
    private final SelenideElement loginButton = Selenide.$x("//input[@name='submitLogin']");
}
