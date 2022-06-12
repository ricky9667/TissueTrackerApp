package com.example.android.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.AddToiletActivity;
import com.example.android.R;
import com.example.android.Store;
import com.example.android.models.Restroom;
import com.example.android.recyclerviews.ToiletListAdapter;

public class ToiletActivity extends AppCompatActivity {
    private Restroom restroom = null;
    private RecyclerView mRecyclerView;
    private final Store store = Store.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toilet_activity);

        int restroomIndex = store.getShowingRestroomIndex();
        if (restroomIndex != -1) {
            restroom = store.getRestroom(restroomIndex);
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