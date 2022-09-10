package com.example.android.viewModel;

import android.util.Log;

import com.example.android.model.Toilet;
import com.example.android.service.BackendClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ToiletsViewModel {
    private final BackendClient _client = BackendClient.getInstance();
    private final ToiletListAdapter _toiletListAdapter;

    public ToiletsViewModel(ToiletListAdapter adapter) {
        _toiletListAdapter = adapter;
    }

    public void loadToiletsData(String restroomId) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("restroomId", restroomId);

            String result = _client.fetchMultipleToilets(jsonObject.toString());

            if (result != null) {
                try {
                    ArrayList<Toilet> toiletList = _toiletListAdapter.getToiletList();
                    _toiletListAdapter.getToiletList().clear();
                    JSONObject rootObject = new JSONObject(result);
                    JSONArray toiletArray = rootObject.getJSONArray("toilets");
                    for (int i = 0; i < toiletArray.length(); i++) {
                        JSONObject toiletObject = toiletArray.getJSONObject(i);
                        Toilet toilet = Toilet.fromJson(toiletObject);
                        toiletList.add(toilet);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void removeToilet(String toiletId, String restroomId) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("toiletId", toiletId);
            jsonObject.put("restroomId", restroomId);

            String response = _client.removeToilet(jsonObject.toString());
            Log.d("removeToilet", response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
