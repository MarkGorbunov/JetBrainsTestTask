package com.api;

import client.Client;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class CreateUserTest {
    @Test
    void apiUserCreate() {
        Response response = Client.getClient().when().get("/users");
        assert response.statusCode() == 200;
        response.then().assertThat().body("users.user.@username", Matchers.hasItems
                ("admin"));
    }

}
