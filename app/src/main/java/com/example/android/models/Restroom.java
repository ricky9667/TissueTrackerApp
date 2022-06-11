package com.example.android.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Restroom extends ArrayList<Toilet> {
    private String mId;
    private String mLocation;
    private ArrayList<Toilet> mToiletList;

    public Restroom(String id, String location) {
        mId = id;
        mLocation = location;
        mToiletList = new ArrayList<>();
    }

    public String getId() {
        return mId;
    }
    //    public void setId(String id) { mId = id; }
    public String getLocation() {
        return mLocation;
    }
    //    public void setLocation(String location) { mLocation = location; }
    public ArrayList<Toilet> getToiletList() {
        return mToiletList;
    }
    public void addToiletList(Toilet toilet) {
        mToiletList.add(toilet);
    }
}
