package com.example.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.models.Toilet;
import com.example.android.models.ToiletState;

import java.util.LinkedList;

public class ToiletActivity extends AppCompatActivity {
    private final LinkedList<Toilet> mToiletList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private ToiletListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toilet_activity);
//        Intent intent = getIntent();
//        int position = Integer.parseInt(intent.getStringExtra("com.example.android.extra.MESSAGE"));


        mToiletList.addLast(new Toilet("5fc04173", "科研 13-1", ToiletState.SUFFICIENT, 1.0f));
        mToiletList.addLast(new Toilet("a9e9ade6", "科研 13-2", ToiletState.INSUFFICIENT, 0.02f));
        mToiletList.addLast(new Toilet("022837b5", "科研 13-3", ToiletState.SUFFICIENT, 0.8f));
        mToiletList.addLast(new Toilet("171fba74", "科研 13-4", ToiletState.SUFFICIENT, 0.72f));
        mToiletList.addLast(new Toilet("7fda5054", "科研 13-5", ToiletState.DISCONNECTED, -1.0f));
        mToiletList.addLast(new Toilet("2d71c162", "科研 13-6", ToiletState.DISCONNECTED, -1.0f));
        mToiletList.addLast(new Toilet("fde245cb", "科研 13-7", ToiletState.INSUFFICIENT, 0.09f));

        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerView);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new ToiletListAdapter(this, mToiletList);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}