package elements.dropdown;

import com.codeborne.selenide.*;

public class MenuListItems {
    private ElementsCollection dropdownOptions = Selenide.$$x("//ul[@class='menuList']/li");

    public void selectOptionFromDropdown(String option) {
        dropdownOptions.shouldBe(CollectionCondition.sizeGreaterThan(0));
        dropdownOptions.find(Condition.innerText(option)).click();
    }
}
