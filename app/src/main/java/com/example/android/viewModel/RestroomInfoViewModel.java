package com.example.android.viewModel;

import android.util.Log;

import com.example.android.service.BackendClient;

import org.json.JSONObject;


public class RestroomInfoViewModel {
    private final BackendClient _client = BackendClient.getInstance();

    public void registerRestroom(String location) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("location", location);

            String response = _client.registerRestroom(jsonObject.toString());
            Log.d("registerRestroom", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateRestroomLocation(int id, String location) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("restroomId", id);
            jsonObject.put("location", location);

            String response = _client.updateRestroomLocation(jsonObject.toString());
            Log.d("updateRestroomLocation", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
