package com.example.android.classes;

import java.util.ArrayList;

public class Restroom extends ArrayList<Toilet> {
    private String mId;
    private String mLocation;
    private final ArrayList<Toilet> mToiletList;

    public Restroom(String id, String location) {
        mId = id;
        mLocation = location;
        mToiletList = new ArrayList<>();
    }

    public String getId() {
        return mId;
    }
    public void setId(String id) { mId = id; }
    public String getLocation() {
        return mLocation;
    }
    public void setLocation(String location) { mLocation = location; }
    public ArrayList<Toilet> getToiletList() {
        return mToiletList;
    }
    public void addToilet(Toilet toilet) {
        mToiletList.add(toilet);
    }
    public void deleteToilet(int position) {
        mToiletList.remove(position);
    }
}
