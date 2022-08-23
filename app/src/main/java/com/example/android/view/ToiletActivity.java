package com.example.android.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.R;
import com.example.android.viewModel.ToiletListAdapter;
import com.example.android.model.Restroom;
import com.example.android.service.Store;

public class ToiletActivity extends AppCompatActivity {
    private final Store store = Store.getInstance();
    private Restroom restroom = null;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toilet_activity);

        int restroomIndex = store.getShowingRestroomIndex();
        if (restroomIndex != -1) {
            restroom = store.getRestroom(restroomIndex);
            setTitle(restroom.getLocation());
        }

        ToiletListAdapter mAdapter = new ToiletListAdapter(this, restroom.getToiletList());
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void addNewToilet(View view) {
        Intent intent = new Intent(view.getContext(), AddToiletActivity.class);
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