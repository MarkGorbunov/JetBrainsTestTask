package pages.administration;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class UsersPage {
    private final SelenideElement createUserLink = $x("//a[contains(@href, 'createUser')]");
    private final SelenideElement searchInput = $x("//input[@id='keyword']");
    private final SelenideElement filterButton = $x("//input[@name='submitFilter']");
    private final SelenideElement resetSearch = $x("//a[@class='reset ']");
    //Create user tab
    private final SelenideElement username = $x("//input[@id='input_teamcityUsername']");
    private final SelenideElement password = $x("//input[@id='password1']");
    private final SelenideElement confirmPassword = $x("//input[@id='retypedPassword']");
    private final SelenideElement adminPermissionsCheckbox = $x("//input[@id='administrator']");
    private final SelenideElement createUserButton = $x("//input[@name='submitCreateUser']");
    //Table
    //TODO check if common table element required
    private final ElementsCollection usersRows = $$x("//table[contains(@class, 'userList')]//tr");
    private final ElementsCollection rowsUsernames = $$x("//table[contains(@class, 'userList')]//tr/td[@class='username highlight']");
    private final ElementsCollection rowsCheckboxes = $$x("//table[contains(@class, 'userList')]//tr//input[@id='userId']");
    private final ElementsCollection rowsRoles = $$x("//table[contains(@class, 'userList')]//tr/td[@class='highlight roles']");
    private final SelenideElement assignedRole = $x("(//div[@class='authorityRolesPopup']//td[@class='roleName'])[1]");
    //usersInteractionOptions
    private final SelenideElement removeUsersButton = $x("//a[@onclick='return BS.UserListForm.removeSelected();']");
}
