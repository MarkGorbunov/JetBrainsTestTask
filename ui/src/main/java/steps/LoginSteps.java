package steps;

import com.codeborne.selenide.Condition;
import com.models.User;
import pages.LoginPage;

public class LoginSteps {
    LoginPage loginPage = new LoginPage();

    public ProjectsSteps login(String username, String password) {
        loginPage.getUsernameInput().setValue(username);
        loginPage.getPasswordInput().setValue(password);
        loginPage.getLoginButton().click();
        loginPage.getLoginButton().should(Condition.hidden);
        return new ProjectsSteps();
    }

    public ProjectsSteps login(User user) {
        return login(user.getUsername(), user.getUsername());
    }
}
