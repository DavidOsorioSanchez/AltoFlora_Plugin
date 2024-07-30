package com.altoflora.utils;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class Axios {
    private final HttpClient client;

    public Axios() {
        this.client = HttpClient.newHttpClient();
    }

    public CompletableFuture<String> get(String url, Map<String, String> headers) {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET();

        headers.forEach(requestBuilder::header);
        HttpRequest request = requestBuilder.build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    public CompletableFuture<String> post(String url, String jsonBody, Map<String, String> headers) {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(jsonBody));

        headers.forEach(requestBuilder::header);
        HttpRequest request = requestBuilder.build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    public CompletableFuture<String> put(String url, String jsonBody, Map<String, String> headers) {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .PUT(BodyPublishers.ofString(jsonBody));

        headers.forEach(requestBuilder::header);
        HttpRequest request = requestBuilder.build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    public CompletableFuture<String> delete(String url, Map<String, String> headers) {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE();
        headers.forEach(requestBuilder::header);
        HttpRequest request = requestBuilder.build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }
}