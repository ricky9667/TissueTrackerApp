package com.example.android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.models.Restroom;
import com.example.android.recyclerviews.RestroomListAdapter;

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

    public void addNewRestroom(View view) {
        Intent intent = new Intent(view.getContext(), AddRestroomActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                int listSize = mRestroomList.size();
                String restroomIdEditText = data.getStringExtra(AddRestroomActivity.EXTRA_RESTROOM_ID);
                String restroomLocationEditText = data.getStringExtra(AddRestroomActivity.EXTRA_RESTROOM_LOCATION);
                mRestroomList.addLast(new Restroom(restroomIdEditText, restroomLocationEditText));
                mRecyclerView.getAdapter().notifyItemInserted(listSize);
                mRecyclerView.smoothScrollToPosition(listSize);
            }
        }
    }
}