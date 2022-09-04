package com.example.android.service;

import com.example.android.model.Restroom;
import com.example.android.model.Toilet;
import com.example.android.model.ToiletState;

import java.util.ArrayList;


// TODO: Store will be completely removed after all data is replaced from backend APIs
public class Store {
    private static Store instance = null;
    private final ArrayList<Restroom> mRestroomList = new ArrayList<>();
    private int mShowingRestroomIndex = -1;

    private Store() { }

    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
            instance.generateFakeData();
        }
        return instance;
    }

    private void generateFakeData() {
        instance.addRestroom(new Restroom("1", "二教 1 樓"));
        instance.addRestroom(new Restroom("2", "二教 2 樓"));
        instance.addRestroom(new Restroom("3", "二教 3 樓"));

        instance.addToilet(0, new Toilet("5fc04173", 1.0f, 1.0f, 1.0f, "科研 13-1", ToiletState.SUFFICIENT));
        instance.addToilet(0, new Toilet("a9e9ade6", 0.02f, 0.02f, 0.02f, "科研 13-2", ToiletState.INSUFFICIENT));
        instance.addToilet(0, new Toilet("022837b5", 0.8f, 0.8f, 0.8f, "科研 13-3", ToiletState.SUFFICIENT));
        instance.addToilet(0, new Toilet("171fba74", 0.72f, 0.72f, 0.72f, "科研 13-4", ToiletState.SUFFICIENT));
        instance.addToilet(0, new Toilet("7fda5054", -1.0f, -1.0f, -1.0f, "科研 13-5", ToiletState.DISCONNECTED));
        instance.addToilet(0, new Toilet("2d71c162", -1.0f, -1.0f, -1.0f, "科研 13-6", ToiletState.DISCONNECTED));
        instance.addToilet(0, new Toilet("fde245cb", 0.09f, 0.09f, 0.09f, "科研 13-7", ToiletState.INSUFFICIENT));

        instance.addToilet(1, new Toilet("5fc04173", 1.0f, 1.0f, 1.0f, "科研 12-1", ToiletState.SUFFICIENT));
        instance.addToilet(1, new Toilet("a9e9ade6", 0.02f, 0.02f, 0.02f, "科研 12-2", ToiletState.INSUFFICIENT));
        instance.addToilet(1, new Toilet("022837b5", 0.8f, 0.8f, 0.8f, "科研 12-3", ToiletState.SUFFICIENT));
        instance.addToilet(1, new Toilet("171fba74", 0.72f, 0.72f, 0.72f, "科研 12-4", ToiletState.SUFFICIENT));
        instance.addToilet(1, new Toilet("7fda5054", -1.0f, -1.0f, -1.0f, "科研 12-5", ToiletState.DISCONNECTED));
        instance.addToilet(1, new Toilet("2d71c162", -1.0f, -1.0f, -1.0f, "科研 12-6", ToiletState.DISCONNECTED));
        instance.addToilet(1, new Toilet("fde245cb", 0.09f, 0.09f, 0.09f, "科研 12-7", ToiletState.INSUFFICIENT));

        instance.addToilet(2, new Toilet("5fc04173", 1.0f, 1.0f, 1.0f, "科研 11-1", ToiletState.SUFFICIENT));
        instance.addToilet(2, new Toilet("a9e9ade6", 0.02f, 0.02f, 0.02f, "科研 11-2", ToiletState.INSUFFICIENT));
        instance.addToilet(2, new Toilet("022837b5", 0.8f, 0.8f, 0.8f, "科研 11-3", ToiletState.SUFFICIENT));
        instance.addToilet(2, new Toilet("171fba74", 0.72f, 0.72f, 0.72f, "科研 11-4", ToiletState.SUFFICIENT));
        instance.addToilet(2, new Toilet("7fda5054", -1.0f, -1.0f, -1.0f, "科研 11-5", ToiletState.DISCONNECTED));
        instance.addToilet(2, new Toilet("2d71c162", -1.0f, -1.0f, -1.0f, "科研 11-6", ToiletState.DISCONNECTED));
        instance.addToilet(2, new Toilet("fde245cb", 0.09f, 0.09f, 0.09f, "科研 11-7", ToiletState.INSUFFICIENT));
    }

    public ArrayList<Restroom> getRestrooms() {
        return mRestroomList;
    }

    public Restroom getRestroom(int position) {
        if (position >= mRestroomList.size()) {
            return null;
        }
        return mRestroomList.get(position);
    }

    public void addRestroom(Restroom restroom) {
        mRestroomList.add(restroom);
    }

    public void deleteRestroom(int position) {
        if (position < mRestroomList.size()) {
            mRestroomList.remove(position);
        }
    }

    public void addToilet(int restroomIndex, Toilet toilet) {
        mRestroomList.get(restroomIndex).addToilet(toilet);
    }

    public void deleteToilet(int restroomIndex, int position) {
        mRestroomList.get(restroomIndex).deleteToilet(position);
    }

    public void setShowingRestroomIndex(int restroomIndex) {
        mShowingRestroomIndex = restroomIndex;
    }

    public int getShowingRestroomIndex() {
        return mShowingRestroomIndex;
    }
}
