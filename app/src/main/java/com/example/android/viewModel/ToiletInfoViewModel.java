package com.example.android.viewModel;

import com.example.android.service.BackendClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ToiletInfoViewModel {
    private final BackendClient _client = BackendClient.getInstance();

    public ToiletInfoViewModel() {
    }

    public void loadUndeployedIds() {
        String result = _client.fetchUndeployedToiletIds();

        if (result != null) {
            try {
                ArrayList<String> undeployedToiletIds = new ArrayList<>();
                JSONObject rootObject = new JSONObject(result);
                JSONArray toiletIdsArray = rootObject.getJSONArray("undeployedToiletIds");
                for (int index = 0; index < toiletIdsArray.length(); index++) {
                    String id = toiletIdsArray.getString(index);
                    undeployedToiletIds.add(id);
                }

                System.out.println("undeployedToiletIds = " + undeployedToiletIds);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
