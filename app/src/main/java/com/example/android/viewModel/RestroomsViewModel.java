package com.example.android.viewModel;

import android.util.Log;

import com.example.android.model.Restroom;
import com.example.android.service.BackendClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class RestroomsViewModel {
    private final BackendClient _client = BackendClient.getInstance();
    private final RestroomListAdapter _restroomListAdapter;

    public RestroomsViewModel(RestroomListAdapter adapter) {
        _restroomListAdapter = adapter;
    }

    public void loadRestroomsData() {
        String result = _client.fetchAllRestrooms();

        if (result != null) {
            try {
                ArrayList<Restroom> restroomList = _restroomListAdapter.getRestroomList();
                _restroomListAdapter.getRestroomList().clear();
                JSONObject rootObject = new JSONObject(result);
                JSONObject restroomsObject = rootObject.getJSONObject("restrooms");
                Iterator<String> keys = restroomsObject.keys();

                while (keys.hasNext()) {
                    String key = keys.next();
                    if (restroomsObject.get(key) instanceof JSONObject) {
                        JSONObject restroomObject = restroomsObject.getJSONObject(key);
                        Restroom restroom = Restroom.fromJson(restroomObject);
                        if (restroom == null) {
                            continue;
                        }
                        restroomList.add(restroom);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteRestroom(String restroomId) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("restroomId", restroomId);

            String response = _client.deleteRestroom(jsonObject.toString());
            Log.d("deleteRestroom", response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
