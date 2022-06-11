package com.example.android.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.AddToiletActivity;
import com.example.android.R;
import com.example.android.models.Restroom;
import com.example.android.recyclerviews.ToiletListAdapter;
import com.example.android.models.Toilet;
import com.example.android.models.ToiletState;

import java.util.ArrayList;

public class ToiletActivity extends AppCompatActivity {
    private ArrayList<Toilet> mToiletList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ToiletListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toilet_activity);

        Intent intent = getIntent();
        int position = Integer.parseInt(intent.getStringExtra("com.example.android.extra.RESTROOM.POSITION"));
        Bundle bundle = intent.getExtras();
        ArrayList<Restroom> list = (ArrayList<Restroom>) bundle.getSerializable("com.example.android.extra.RESTROOM.LIST");
        mToiletList = list.get(position).getToiletList();

        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new ToiletListAdapter(this, mToiletList);
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
                String toiletIdEditText = data.getStringExtra(AddToiletActivity.EXTRA_TOILET_ID);
                String toiletLocationEditText = data.getStringExtra(AddToiletActivity.EXTRA_TOILET_LOCATION);
                mToiletList.add(new Toilet(toiletIdEditText, toiletLocationEditText, ToiletState.DISCONNECTED, -1.0f));
                mRecyclerView.getAdapter().notifyDataSetChanged();
            }
        }
    }
}