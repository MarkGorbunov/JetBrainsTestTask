package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class BuildPage {
    //auto-detect-build
    private final ElementsCollection runnersSteps = $$x("//table[contains(@class, 'parametersTable')]//tr/td[2]");
    private final ElementsCollection runnersCheckbox = $$x("//table[contains(@class, 'parametersTable')]//tr//input[@id='runnerId']");
    private final SelenideElement useSelectedButton = $x("//form[@id='discoveredRunners']//a[@class='btn btn_primary']");
    //build Steps
    private final SelenideElement successMessage = $x("//div[@class='successMessage ']");
}
