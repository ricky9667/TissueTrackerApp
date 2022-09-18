package com.example.android.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.R;
import com.example.android.utils.BasicAsyncTask;
import com.example.android.viewModel.RestroomListAdapter;
import com.example.android.viewModel.RestroomsViewModel;

public class MainActivity extends AppCompatActivity {
    private RestroomsViewModel _viewModel;
    private static final String RESTROOM_INTENT_FLAG = "restroomIntentFlag";
    private RestroomListAdapter _adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _adapter = new RestroomListAdapter(this);
        _viewModel = new RestroomsViewModel(_adapter);

        RecyclerView restroomRecyclerView = findViewById(R.id.restroomRecyclerView);
        restroomRecyclerView.setAdapter(_adapter);
        restroomRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        final Runnable backgroundTask = () -> _viewModel.loadRestroomsData();
        new BasicAsyncTask(backgroundTask, _adapter::notifyDataSetChanged).execute();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                final Runnable backgroundTask = () -> _viewModel.loadRestroomsData();
                new BasicAsyncTask(backgroundTask, _adapter::notifyDataSetChanged).execute();
            }
        }
    }

    public void addNewRestroom(View view) {
        Intent intent = new Intent(view.getContext(), AddRestroomActivity.class);
        intent.putExtra(RESTROOM_INTENT_FLAG, "register");

        startActivityForResult(intent, 1);
    }

    public void updateRestroomLocation(View view) {
        Intent intent = new Intent(view.getContext(), AddRestroomActivity.class);
        intent.putExtra(RESTROOM_INTENT_FLAG, "update");
        startActivityForResult(intent, 1);
    }
}
