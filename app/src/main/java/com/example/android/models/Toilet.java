package com.example.android.models;

public class Toilet{
    private String mId;
    private String mLocation;
    private ToiletState mState;
    private double mPercentage; // percentage of tissue amount

    public Toilet(String id, String location, ToiletState state, double amount) {
        mId = id;
        mLocation = location;
        mState = state;
        mPercentage = amount;
    }

    public String getId() { return mId; }
    public void setId(String id) { mId = id; }
    public String getLocation() { return mLocation; }
    public void setLocation(String location) { mLocation = location; }
    public ToiletState getState() { return mState; }
    public void setState(ToiletState state) { mState = state; }
    public double getPercentage() { return mPercentage; }
    public void setPercentage(double percentage) { mPercentage = percentage; }
}
