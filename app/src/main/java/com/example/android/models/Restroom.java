package com.example.android.models;

import java.util.LinkedList;

public class Restroom {
    private String mId;
    private String mLocation;
    private LinkedList<Toilet> mToiletList;

    public Restroom(String id, String location) {
        mId = id;
        mLocation = location;
        mToiletList = new LinkedList<>();
    }

    public String getId() { return mId; }
    public void setId(String id) { mId = id; }
    public String getLocation() { return mLocation; }
//    public void setLocation(String location) { mLocation = location; }
//    public LinkedList<Toilet> getToiletList() { return mToiletList; }
}
