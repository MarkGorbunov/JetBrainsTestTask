package com.api;

import client.Client;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static client.AuthorizationUtil.token;
import static client.Client.USERS;
import static client.Client.httpRequestBuilder;

public class CreateUserTest {
    @Test
    void apiUserCreate() {
        HttpResponse<String> response1 = new Client()
                .get(httpRequestBuilder(Client.BASE_URL + USERS));
        assert response1.statusCode() == 200;
        System.out.println(response1.body());
    }

    @Test
    void changePermissions() {
        changePerProjectPermissionToTrue();
    }

    @Test
    void changePermissionsFalse() {
        changePerProjectPermissionToFalse();
    }

    public void changePerProjectPermissionToTrue() {
        HttpRequest.Builder request = httpRequestBuilder("http://localhost:8112/admin/authAction.html?action=save")
                .setHeader("Content-Type", "application/x-www-form-urlencoded");
        String body = "guestLoginAllowed=true&_guestLoginAllowed=&guestUsername=guest&textForLoginPage=&_loginFormCollapsed=&twoFactorAuthenticationType=OPTIONAL&perProjectPermissions=true&_perProjectPermissions=&emailVerificationEnabled=true&_emailVerificationEnabled=";
        HttpResponse<String> response = new Client().post(request, body);
    }

    @SneakyThrows
    public void changePerProjectPermissionToFalse() {
        HttpRequest.Builder request = httpRequestBuilder("http://localhost:8112/admin/authAction.html?action=save")
                .setHeader("Content-Type", "application/x-www-form-urlencoded");
        String body = "guestLoginAllowed=true&_guestLoginAllowed=&guestUsername=guest&textForLoginPage=&_loginFormCollapsed=&twoFactorAuthenticationType=OPTIONAL&_perProjectPermissions=&emailVerificationEnabled=true&_emailVerificationEnabled=";
        HttpResponse<String> response = new Client().post(request, body);
    }

}
