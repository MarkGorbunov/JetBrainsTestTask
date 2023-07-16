package client;

import io.restassured.RestAssured;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static client.AuthorizationUtil.token;

public class Client {
    private static final String baseUrl = "http://localhost:8112/app/rest";

    public static RequestSpecification getClient() {
        return RestAssured.given()
                .header("Authorization", "Bearer " + token).
                baseUri(baseUrl).
                accept(ContentType.XML).
                filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new ErrorLoggingFilter());
    }
}


