package client;

import lombok.SneakyThrows;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AdministrationClient extends Client {
    public void changePerProjectPermission(boolean isEnabled) {
        HttpRequest.Builder request = httpRequestBuilder("http://localhost:8112/admin/authAction.html?action=save")
                .setHeader("Content-Type", "application/x-www-form-urlencoded");
        String body;
        if (isEnabled) {
            body = "guestLoginAllowed=true&_guestLoginAllowed=&guestUsername=guest&textForLoginPage=&_loginFormCollapsed=&twoFactorAuthenticationType=OPTIONAL&perProjectPermissions=true&_perProjectPermissions=&emailVerificationEnabled=true&_emailVerificationEnabled=";
        } else {
            body = "guestLoginAllowed=true&_guestLoginAllowed=&guestUsername=guest&textForLoginPage=&_loginFormCollapsed=&twoFactorAuthenticationType=OPTIONAL&_perProjectPermissions=&emailVerificationEnabled=true&_emailVerificationEnabled=";
        }
        HttpResponse<String> response = new Client().post(request, body);
        assert response.statusCode() == 200;
    }
}
