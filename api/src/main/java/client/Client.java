package client;

import com.config.Config;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
                .header("Authorization", "Bearer " + Config.getAdminUser().getToken())
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
}


