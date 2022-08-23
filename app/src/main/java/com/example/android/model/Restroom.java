package com.example.android.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Restroom extends ArrayList<Toilet> {
    private String _id;
    private String _location;
    private final ArrayList<Toilet> _toiletList; // TODO: remove after data is all fetched from backend

    public Restroom(String id, String location) {
        _id = id;
        _location = location;
        _toiletList = new ArrayList<>();
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

    public void setId(String id) {
        _id = id;
    }

    public String getLocation() {
        return _location;
    }

    public void setLocation(String location) {
        _location = location;
    }

    public ArrayList<Toilet> getToiletList() {
        return _toiletList;
    }

    public void addToilet(Toilet toilet) {
        _toiletList.add(toilet);
    }

    public void deleteToilet(int position) {
        _toiletList.remove(position);
    }
}
