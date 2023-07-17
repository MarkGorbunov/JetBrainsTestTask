package client;

import io.restassured.RestAssured;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static client.AuthorizationUtil.token;

public class Client {
    public static final String BASE_URL = "http://localhost:8112/app/rest";
    public static final String USERS = "/users";
    private HttpClient client;

    public Client() {
        client = HttpClient.newHttpClient();
    }

    @SneakyThrows
    public static HttpRequest.Builder httpRequestBuilder(String url) {
        return HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/xml");
    }

    @SneakyThrows
    public HttpResponse<String> post(HttpRequest.Builder request, String body) {
        return client.send(request.POST(HttpRequest.BodyPublishers.ofString(body)).build(), HttpResponse.BodyHandlers.ofString());
    }

    @SneakyThrows
    public HttpResponse<String> get(HttpRequest.Builder request) {
        return client.send(request.GET().build(), HttpResponse.BodyHandlers.ofString());
    }


    public static RequestSpecification getClient() {
        return RestAssured.given()
                .header("Authorization", "Bearer " + token).
                baseUri(BASE_URL).
                accept(ContentType.XML).
                filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new ErrorLoggingFilter());
    }
}


