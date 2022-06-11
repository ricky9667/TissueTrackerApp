package com.example.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.models.Restroom;
import com.example.android.models.Toilet;
import com.example.android.models.ToiletState;
import com.example.android.recyclerviews.RestroomListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final ArrayList<Restroom> mRestroomList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RestroomListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRestroomList.add(new Restroom("765f4794-fa77-4930-8aa3-118bf28f4db8", "二教 1 樓"));
        mRestroomList.add(new Restroom("e9e2dab4-d274-46bd-a4e2-91d2fa4a0e94", "二教 2 樓"));
        mRestroomList.add(new Restroom("4c90606c-aa24-4c70-bb2e-91acb171de01", "二教 3 樓"));

        mRestroomList.get(0).addToiletList(new Toilet("5fc04173", "科研 13-1", ToiletState.SUFFICIENT, 1.0f));
        mRestroomList.get(0).addToiletList(new Toilet("a9e9ade6", "科研 13-2", ToiletState.INSUFFICIENT, 0.02f));
        mRestroomList.get(0).addToiletList(new Toilet("022837b5", "科研 13-3", ToiletState.SUFFICIENT, 0.8f));
        mRestroomList.get(0).addToiletList(new Toilet("171fba74", "科研 13-4", ToiletState.SUFFICIENT, 0.72f));
        mRestroomList.get(0).addToiletList(new Toilet("7fda5054", "科研 13-5", ToiletState.DISCONNECTED, -1.0f));
        mRestroomList.get(0).addToiletList(new Toilet("2d71c162", "科研 13-6", ToiletState.DISCONNECTED, -1.0f));
        mRestroomList.get(0).addToiletList(new Toilet("fde245cb", "科研 13-7", ToiletState.INSUFFICIENT, 0.09f));

        mRestroomList.get(1).addToiletList(new Toilet("5fc04173", "科研 12-1", ToiletState.SUFFICIENT, 1.0f));
        mRestroomList.get(1).addToiletList(new Toilet("a9e9ade6", "科研 12-2", ToiletState.INSUFFICIENT, 0.02f));
        mRestroomList.get(1).addToiletList(new Toilet("022837b5", "科研 12-3", ToiletState.SUFFICIENT, 0.8f));
        mRestroomList.get(1).addToiletList(new Toilet("171fba74", "科研 12-4", ToiletState.SUFFICIENT, 0.72f));
        mRestroomList.get(1).addToiletList(new Toilet("7fda5054", "科研 12-5", ToiletState.DISCONNECTED, -1.0f));
        mRestroomList.get(1).addToiletList(new Toilet("2d71c162", "科研 12-6", ToiletState.DISCONNECTED, -1.0f));
        mRestroomList.get(1).addToiletList(new Toilet("fde245cb", "科研 12-7", ToiletState.INSUFFICIENT, 0.09f));

        mRestroomList.get(2).addToiletList(new Toilet("5fc04173", "科研 11-1", ToiletState.SUFFICIENT, 1.0f));
        mRestroomList.get(2).addToiletList(new Toilet("a9e9ade6", "科研 11-2", ToiletState.INSUFFICIENT, 0.02f));
        mRestroomList.get(2).addToiletList(new Toilet("022837b5", "科研 11-3", ToiletState.SUFFICIENT, 0.8f));
        mRestroomList.get(2).addToiletList(new Toilet("171fba74", "科研 11-4", ToiletState.SUFFICIENT, 0.72f));
        mRestroomList.get(2).addToiletList(new Toilet("7fda5054", "科研 11-5", ToiletState.DISCONNECTED, -1.0f));
        mRestroomList.get(2).addToiletList(new Toilet("2d71c162", "科研 11-6", ToiletState.DISCONNECTED, -1.0f));
        mRestroomList.get(2).addToiletList(new Toilet("fde245cb", "科研 11-7", ToiletState.INSUFFICIENT, 0.09f));

        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new RestroomListAdapter(this, mRestroomList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void addNewRestroom(View view) {
        Intent intent = new Intent(view.getContext(), AddRestroomActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String restroomIdEditText = data.getStringExtra(AddRestroomActivity.EXTRA_RESTROOM_ID);
                String restroomLocationEditText = data.getStringExtra(AddRestroomActivity.EXTRA_RESTROOM_LOCATION);
                mRestroomList.add(new Restroom(restroomIdEditText, restroomLocationEditText));
                mRecyclerView.getAdapter().notifyDataSetChanged();
            }
        }
    }
}