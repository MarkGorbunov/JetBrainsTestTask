package com.api;

import body.JsonUtil;
import client.Client;
import com.models.Role;
import com.models.Roles;
import com.models.RolesModel;
import com.models.User;
import com.utils.RandomUtil;
import org.testng.annotations.Test;
import url.UrlUtils;

import java.net.http.HttpResponse;
import java.util.List;

import static client.Client.httpRequestBuilder;

public class CreateUserApiTest {

    User user = User.builder()
            .username(RandomUtil.generateRandomName())
            .password(RandomUtil.generateRandomNumbers(5))
            .roles(new Roles(List.of(new Role(RolesModel.PROJECT_ADMIN, "g"))))
            .isAdmin(false)
            .build();

    @Test(groups = "Api")
    void sendCreateUserRequest() {
        String jsonBody = JsonUtil.convertObjectToJson(user);
        HttpResponse<String> response = new Client()
                .post(httpRequestBuilder(UrlUtils.USERS_URL), jsonBody);
        assert response.statusCode() == 200;
    }

    @Test(groups = "Api", dependsOnMethods = "sendCreateUserRequest")
    void verifyRetrieveUserByUsernameReturnCorrectData() {
        HttpResponse<String> response = new Client()
                .get(httpRequestBuilder(UrlUtils.USERS_URL + "/username:" + user.getUsername()));
        assert response.statusCode() == 200;
        assert response.body().contains(user.getUsername());
    }
}
