package client;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static url.UrlUtils.ADMIN_AUTH_URL;

public class AdministrationClient extends Client {
    public void changePerProjectPermission(boolean isEnabled) {
        HttpRequest.Builder request = httpRequestBuilder(ADMIN_AUTH_URL)
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
