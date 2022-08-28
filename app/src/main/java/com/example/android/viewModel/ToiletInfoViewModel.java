package com.example.android.viewModel;

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

                System.out.println("undeployedToiletIds = " + _undeployedToiletIds);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
