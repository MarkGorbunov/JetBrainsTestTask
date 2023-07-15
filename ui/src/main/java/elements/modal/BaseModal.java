package elements.modal;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.function.Supplier;

public class BaseModal<T> {
    private SelenideElement baseElement = Selenide.$x("//div[@data-modal]");
    private SelenideElement closeModalButton = baseElement.$x(".//a[@class='baseElement']");
    private SelenideElement modalName = baseElement.$x(".//h3[@class='dialogTitle']");
    protected SelenideElement primaryButton = baseElement.$x(".//div[@class='popupSaveButtonsBlock']/input");
    private Supplier<T> child;

    protected BaseModal(Supplier<T> child) {
        this.child = child;
    }

    public T verifyModalName(String name) {
        modalName.should(Condition.text(name));
        return child.get();
    }
}
