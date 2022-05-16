package com.example.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.LinkedList;

public class ToiletActivity extends AppCompatActivity {
    private final LinkedList<String> mToiletList = new LinkedList<>();
    private final LinkedList<String> mToiletStateList = new LinkedList<>();
    private final LinkedList<String> mToiletIdList = new LinkedList<>();
    private final LinkedList<String> mToiletLocationList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private ToiletListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toilet_activity);
//        Intent intent = getIntent();
//        int position = Integer.parseInt(intent.getStringExtra("com.example.android.extra.MESSAGE"));

        mToiletList.addLast("Fruit Pizza");
        mToiletStateList.addLast("sufficient\n");
        mToiletIdList.addLast("0123456789abcdef0123\n");
        mToiletLocationList.addLast("1-1");
        mToiletList.addLast("The Best Soft Chocolate Chip Cookies");
        mToiletStateList.addLast("sufficient\n");
        mToiletIdList.addLast("0123456789abcdef0123\n");
        mToiletLocationList.addLast("1-2");
        mToiletList.addLast("Blender Lemon Pie");
        mToiletStateList.addLast("sufficient\n");
        mToiletIdList.addLast("0123456789abcdef0123\n");
        mToiletLocationList.addLast("1-3");
        mToiletList.addLast("Modern Scotcheroos");
        mToiletStateList.addLast("sufficient\n");
        mToiletIdList.addLast("0123456789abcdef0123\n");
        mToiletLocationList.addLast("1-4");
        mToiletList.addLast("Raspberry Crumble Bars");
        mToiletStateList.addLast("sufficient\n");
        mToiletIdList.addLast("0123456789abcdef0123\n");
        mToiletLocationList.addLast("1-5");
        mToiletList.addLast("Mind-Blowing Vegan Chocolate Pie");
        mToiletStateList.addLast("sufficient\n");
        mToiletIdList.addLast("0123456789abcdef0123\n");
        mToiletLocationList.addLast("1-6");
        mToiletList.addLast("Gooey Caramel Monkey Bread");
        mToiletStateList.addLast("sufficient\n");
        mToiletIdList.addLast("0123456789abcdef0123\n");
        mToiletLocationList.addLast("1-7");
        mToiletList.addLast("Raw Salted Chocolate Snack Bars");
        mToiletStateList.addLast("sufficient\n");
        mToiletIdList.addLast("0123456789abcdef0123\n");
        mToiletLocationList.addLast("1-8");
        mToiletList.addLast("Carrot Cake Coffee Cake");
        mToiletStateList.addLast("sufficient\n");
        mToiletIdList.addLast("0123456789abcdef0123\n");
        mToiletLocationList.addLast("1-9");
        mToiletList.addLast("Almond Butter Cups");
        mToiletStateList.addLast("sufficient\n");
        mToiletIdList.addLast("0123456789abcdef0123\n");
        mToiletLocationList.addLast("1-10");
        mToiletList.addLast("Best Peach Cobbler");
        mToiletStateList.addLast("sufficient\n");
        mToiletIdList.addLast("0123456789abcdef0123\n");
        mToiletLocationList.addLast("1-11");

        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerView);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new ToiletListAdapter(this, mToiletList, mToiletStateList, mToiletIdList, mToiletLocationList);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}