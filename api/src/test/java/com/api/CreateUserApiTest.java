package com.api;

import client.Client;
import org.testng.annotations.Test;

import java.net.http.HttpResponse;

import static client.Client.USERS;
import static client.Client.httpRequestBuilder;

public class CreateUserApiTest {
    @Test(groups = "Api")
    void apiUserCreate() {
        HttpResponse<String> response1 = new Client()
                .get(httpRequestBuilder(Client.BASE_URL + USERS));
        assert response1.statusCode() == 200;
        System.out.println(response1.body());
    }
}
