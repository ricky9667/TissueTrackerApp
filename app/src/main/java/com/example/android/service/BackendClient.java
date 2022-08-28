package com.example.android.service;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BackendClient {
    private static BackendClient instance = null;
    private final OkHttpClient _client;
    private final String _baseUrl = "https://c4ee-2001-b011-4002-1050-d958-3e2b-344c-2667.jp.ngrok.io";

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

    public String fetchMultipleToilets(String restroomId) {
        final String path = "/toilets";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("restroomId", restroomId);
        JSONObject jsonObject = new JSONObject(data);
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        Request request = new Request.Builder().url(_baseUrl + path).post(body).build();
        try (Response response = _client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
