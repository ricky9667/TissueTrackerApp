package com.example.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.recyclerviews.RestroomListAdapter;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RestroomListAdapter mAdapter;
    private final Store store = Store.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new RestroomListAdapter(this, store.getRestrooms());
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
                mRecyclerView.getAdapter().notifyDataSetChanged();
            }
        }
    }
}