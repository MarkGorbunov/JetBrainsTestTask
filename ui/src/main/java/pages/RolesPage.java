package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;

@Getter
public class RolesPage {
    private SelenideElement assignRoleButton = $x("//a[@class='btn assignRoleBtn']");
    private final SelenideElement successMessage = $x("//div[@class='successMessage' and not(@style='display: none; ')]");// TODO thing about introducing common

}
