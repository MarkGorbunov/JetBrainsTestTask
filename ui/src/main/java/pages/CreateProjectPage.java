package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;

@Getter
public class CreateProjectPage {
    private final SelenideElement fromRepositoryTab = $x("//a[@name='createFromUrl']/following-sibling::a");
    private final SelenideElement manuallyTab = $x("//a[@name='createManually']/following-sibling::a");
    private final SelenideElement parentProjectButton = $x("//div[@id='parentIdSelect']");
    private final SelenideElement repositoryUrlInput = $x("//input[@name='url']");
    private final SelenideElement nextButton = $x("//div[@class='saveButtonsBlock']/input[@type='submit']");
    private final SelenideElement nameInput = $x("//input[@id='name']");
    private final SelenideElement projectIdInput = $x("//input[@id='externalId']");
    private final SelenideElement projectNameInput = $x("//input[@id='projectName']");
    private final SelenideElement buildNameInput = $x("//input[@id='buildTypeName']");
}
