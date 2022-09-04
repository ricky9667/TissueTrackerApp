package com.example.android.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Toilet{
    private String _id;
    private double _percentage; // percentage of tissue amount
    private double _distance;
    private double _maxDistance;
    private String _location;
    private ToiletState _state;

    public Toilet(String id, double percentage, double distance, double maxDistance, String location, ToiletState state) {
        _id = id;
        _percentage = percentage;
        _distance = distance;
        _maxDistance = maxDistance;
        _location = location;
        _state = state;
    }

    public static Toilet fromJson(JSONObject toiletObject) {
        try {
            String id = toiletObject.getString("id");
            double percentage = toiletObject.getDouble("percentage");
            double distance = toiletObject.getDouble("distance");
            double maxDistance = toiletObject.getDouble("maxDistance");
            String location = toiletObject.getString("location");
            ToiletState state = ToiletState.valueOf(toiletObject.getString("state").toUpperCase());
            return new Toilet(id, percentage, distance, maxDistance, location, state);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getId() { return _id; }
    public void setId(String id) { _id = id; }
    public double getPercentage() { return _percentage; }
    public void setPercentage(double percentage) { _percentage = percentage; }
    public double getDistance() { return _distance; }
    public void setDistance(double distance) { _distance = distance; }
    public double getMaxDistance() { return _maxDistance; }
    public void setMaxDistance(double maxDistance) { _maxDistance = maxDistance; }
    public String getLocation() { return _location; }
    public void setLocation(String location) { _location = location; }
    public ToiletState getState() { return _state; }
    public void setState(ToiletState state) { _state = state; }
}
