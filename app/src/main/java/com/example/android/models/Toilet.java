package com.example.android.models;

public class Toilet {
    private String mId;
    private String mLocation;
    private ToiletState mState;
    private double mPercentage; // percentage of tissue amount

    public Toilet(String id, String location, ToiletState state, double amount) {
        this.mId = id;
        this.mLocation = location;
        this.mState = state;
        this.mPercentage = amount;
    }

    public String getId() { return mId; }
    public void setId(String id) { mId = id; }
    public String getLocation() { return mLocation; }
    public void setLocation(String location) { mLocation = location; }
    public ToiletState getState() { return mState; }
    public void setState(ToiletState state) { mState = state; }
    public double getPercentage() { return mPercentage; }
    public void setId(double percentage) { mPercentage = percentage; }
}
