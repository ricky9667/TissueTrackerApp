package com.example.android.service;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BackendClient {
    private static BackendClient instance = null;
    private final OkHttpClient _client;
    private final String _baseUrl = "https://8c21-219-85-130-217.jp.ngrok.io";

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
}
