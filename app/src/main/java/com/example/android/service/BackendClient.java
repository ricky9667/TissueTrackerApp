package com.example.android.service;

import android.util.Log;

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
    private final String _baseUrl = "https://b8dd-2001-b011-2019-1f37-8887-981f-8320-7ad5.jp.ngrok.io";
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

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
