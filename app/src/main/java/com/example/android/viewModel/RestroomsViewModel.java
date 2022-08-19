package com.example.android.viewModel;

import com.example.android.model.Restroom;
import com.example.android.service.BackendClient;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class RestroomsViewModel {
    private final BackendClient _client = BackendClient.getInstance();
    private final RestroomListAdapter _restroomListAdapter;

    public RestroomsViewModel(RestroomListAdapter adapter) {
        _restroomListAdapter = adapter;
    }

    public void fetchRestroomsData() {
        String result = _client.getAllRestrooms();

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
}
