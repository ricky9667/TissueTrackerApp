package com.example.android.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Restroom {
    private final String _id;
    private final String _location;

    public Restroom(String id, String location) {
        _id = id;
        _location = location;
    }

    public static Restroom fromJson(JSONObject restroomObject) {
        try {
            String id = restroomObject.getString("id");
            String location = restroomObject.getString("location");
            return new Restroom(id, location);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getId() {
        return _id;
    }

    public String getLocation() {
        return _location;
    }
}
