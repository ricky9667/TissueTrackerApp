package com.example.android.viewModel;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.R;
import com.example.android.model.Restroom;
import com.example.android.service.BackendClient;
import com.example.android.service.Store;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class RestroomsViewModel {
    private final Store _store = Store.getInstance();
    private final BackendClient _client = BackendClient.getInstance();
    private final ArrayList<Restroom> _restroomList;

    public RestroomsViewModel() {
        _restroomList = new ArrayList<>();
        _restroomList.addAll(_store.getRestrooms());
    }

    public ArrayList<Restroom> getRestroomList() {
        return _restroomList;
    }

    public void fetchRestroomsData() {
        String result = _client.getAllRestrooms();
        System.out.println("result = " + result);

        if (result != null) {
            try {
                ArrayList<Restroom> restroomList = new ArrayList<>();
                JSONObject rootObject = new JSONObject(result);
                JSONObject restroomsObject = rootObject.getJSONObject("restrooms");
                Iterator<String> keys = restroomsObject.keys();

                while (keys.hasNext()) {
                    String key = keys.next();
                    if (restroomsObject.get(key) instanceof JSONObject) {
                        JSONObject restroomObject = restroomsObject.getJSONObject(key);
                        Restroom restroom = Restroom.fromJson(restroomObject);
                        if (restroom == null) {
                            System.out.println("restroom with key " + key + " is skipped");
                            continue;
                        }
                        System.out.println("id = " + restroom.getId() + ", location = " + restroom.getLocation());
                        restroomList.add(restroom);
                    }
                }

                _restroomList.clear();
                _restroomList.addAll(restroomList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
