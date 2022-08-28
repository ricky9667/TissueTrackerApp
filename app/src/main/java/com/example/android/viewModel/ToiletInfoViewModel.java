package com.example.android.viewModel;

import android.util.Log;

import com.example.android.service.BackendClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ToiletInfoViewModel {
    private final BackendClient _client = BackendClient.getInstance();
    private final ArrayList<String> _undeployedToiletIds;

    public ToiletInfoViewModel(ArrayList<String> arrayList) {
        _undeployedToiletIds = arrayList;
    }

    public void loadUndeployedIds() {
        String result = _client.fetchUndeployedToiletIds();

        if (result != null) {
            try {
                _undeployedToiletIds.clear();
                JSONObject rootObject = new JSONObject(result);
                JSONArray toiletIdsArray = rootObject.getJSONArray("undeployedToiletIds");
                for (int index = 0; index < toiletIdsArray.length(); index++) {
                    String id = toiletIdsArray.getString(index);
                    _undeployedToiletIds.add(id);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void registerToilet(String restroomId, String toiletId, String location) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("restroomId", restroomId);
            jsonObject.put("toiletId", toiletId);
            jsonObject.put("location", location);

            String response = _client.registerToilet(jsonObject.toString());
            Log.d("registerToilet", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
