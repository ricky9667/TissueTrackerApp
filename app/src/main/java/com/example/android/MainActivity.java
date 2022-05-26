package com.example.android;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.models.Restroom;
import com.example.android.models.Toilet;
import com.example.android.models.ToiletState;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private final LinkedList<Restroom> mRestroomList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private RestroomListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRestroomList.addLast(new Restroom("765f4794-fa77-4930-8aa3-118bf28f4db8", "二教 1 樓"));
        mRestroomList.addLast(new Restroom("e9e2dab4-d274-46bd-a4e2-91d2fa4a0e94", "二教 2 樓"));
        mRestroomList.addLast(new Restroom("4c90606c-aa24-4c70-bb2e-91acb171de01", "二教 3 樓"));

        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new RestroomListAdapter(this, mRestroomList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}