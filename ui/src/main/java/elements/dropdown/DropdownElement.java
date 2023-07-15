package elements.dropdown;

import com.codeborne.selenide.*;

public class DropdownElement {
    private SelenideElement baseElement = Selenide.$x("//div[@data-test='ring-popup']");
    private ElementsCollection dropdownOptions = baseElement.$$x(".//div[@role='row']");

    public void selectOptionFromDropdown(String option) {
        dropdownOptions.shouldBe(CollectionCondition.sizeGreaterThan(0));
        dropdownOptions.find(Condition.text(option)).click();
    }
}
