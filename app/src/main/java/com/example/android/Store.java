package com.example.android;

import com.example.android.models.Restroom;
import com.example.android.models.Toilet;
import com.example.android.models.ToiletState;

import java.util.ArrayList;

public class Store {
    private static Store instance = null;
    private final ArrayList<Restroom> mRestroomList = new ArrayList<>();

    private Store() { }

    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
            instance.generateFakeData();
        }
        return instance;
    }

    private void generateFakeData() {
        instance.addRestroom(new Restroom("765f4794-fa77-4930-8aa3-118bf28f4db8", "二教 1 樓"));
        instance.addRestroom(new Restroom("e9e2dab4-d274-46bd-a4e2-91d2fa4a0e94", "二教 2 樓"));
        instance.addRestroom(new Restroom("4c90606c-aa24-4c70-bb2e-91acb171de01", "二教 3 樓"));

        instance.addToilet(0, new Toilet("5fc04173", "科研 13-1", ToiletState.SUFFICIENT, 1.0f));
        instance.addToilet(0, new Toilet("a9e9ade6", "科研 13-2", ToiletState.INSUFFICIENT, 0.02f));
        instance.addToilet(0, new Toilet("022837b5", "科研 13-3", ToiletState.SUFFICIENT, 0.8f));
        instance.addToilet(0, new Toilet("171fba74", "科研 13-4", ToiletState.SUFFICIENT, 0.72f));
        instance.addToilet(0, new Toilet("7fda5054", "科研 13-5", ToiletState.DISCONNECTED, -1.0f));
        instance.addToilet(0, new Toilet("2d71c162", "科研 13-6", ToiletState.DISCONNECTED, -1.0f));
        instance.addToilet(0, new Toilet("fde245cb", "科研 13-7", ToiletState.INSUFFICIENT, 0.09f));

        instance.addToilet(1, new Toilet("5fc04173", "科研 12-1", ToiletState.SUFFICIENT, 1.0f));
        instance.addToilet(1, new Toilet("a9e9ade6", "科研 12-2", ToiletState.INSUFFICIENT, 0.02f));
        instance.addToilet(1, new Toilet("022837b5", "科研 12-3", ToiletState.SUFFICIENT, 0.8f));
        instance.addToilet(1, new Toilet("171fba74", "科研 12-4", ToiletState.SUFFICIENT, 0.72f));
        instance.addToilet(1, new Toilet("7fda5054", "科研 12-5", ToiletState.DISCONNECTED, -1.0f));
        instance.addToilet(1, new Toilet("2d71c162", "科研 12-6", ToiletState.DISCONNECTED, -1.0f));
        instance.addToilet(1, new Toilet("fde245cb", "科研 12-7", ToiletState.INSUFFICIENT, 0.09f));

        instance.addToilet(2, new Toilet("5fc04173", "科研 11-1", ToiletState.SUFFICIENT, 1.0f));
        instance.addToilet(2, new Toilet("a9e9ade6", "科研 11-2", ToiletState.INSUFFICIENT, 0.02f));
        instance.addToilet(2, new Toilet("022837b5", "科研 11-3", ToiletState.SUFFICIENT, 0.8f));
        instance.addToilet(2, new Toilet("171fba74", "科研 11-4", ToiletState.SUFFICIENT, 0.72f));
        instance.addToilet(2, new Toilet("7fda5054", "科研 11-5", ToiletState.DISCONNECTED, -1.0f));
        instance.addToilet(2, new Toilet("2d71c162", "科研 11-6", ToiletState.DISCONNECTED, -1.0f));
        instance.addToilet(2, new Toilet("fde245cb", "科研 11-7", ToiletState.INSUFFICIENT, 0.09f));

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

    public void addToilet(int restroomIndex, Toilet toilet) {
        mRestroomList.get(restroomIndex).addToilet(toilet);
    }
}
