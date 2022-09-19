package com.example.android.service;

import java.io.IOException;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BackendClient {
    private static BackendClient instance = null;
    private final OkHttpClient _client;
    private final String _baseUrl = "http://140.124.181.106:8080";
    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private BackendClient() {
        _client = new OkHttpClient();
    }

    public static BackendClient getInstance() {
        if (instance == null) {
            instance = new BackendClient();
        }
        return instance;
    }

    public String fetchAllRestrooms() {
        final String path = "/restrooms";
        Request request = new Request.Builder().url(_baseUrl + path).build();
        try (Response response = _client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String fetchUndeployedToiletIds() {
        final String path = "/undeployedToiletIds";
        Request request = new Request.Builder().url(_baseUrl + path).build();
        try (Response response = _client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String deleteRestroom(String json) {
        final String path = "/restroom";
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(_baseUrl + path).delete(body).build();
        try (Response response = _client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String registerToilet(String json) {
        final String path = "/toilet";
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(_baseUrl + path)
                .post(body)
                .build();
        try (Response response = _client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String fetchMultipleToilets(String json) {
        final String path = "/toilets";
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(_baseUrl + path).post(body).build();
        try (Response response = _client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String removeToilet(String json) {
        final String path = "/toilet";
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(_baseUrl + path).delete(body).build();
        try (Response response = _client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String registerRestroom(String json) {
        final String path = "/restroom";
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(_baseUrl + path)
                .post(body)
                .build();
        try (Response response = _client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String updateRestroomLocation(String json) {
        final String path = "/restroom";
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(_baseUrl + path)
                .put(body)
                .build();
        try (Response response = _client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
