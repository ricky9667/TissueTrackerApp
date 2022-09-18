package com.example.android.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.R;
import com.example.android.utils.BasicAsyncTask;
import com.example.android.viewModel.ToiletListAdapter;
import com.example.android.viewModel.ToiletsViewModel;

public class ToiletActivity extends AppCompatActivity {
    private ToiletsViewModel _viewModel;
    private ToiletListAdapter _adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toilet_activity);
        String restroomId = getIntent().getStringExtra("restroomId");
        String restroomLocation = getIntent().getStringExtra("restroomLocation");

        if (restroomId != null) {
            _adapter = new ToiletListAdapter(this);
            _viewModel = new ToiletsViewModel(_adapter);

            RecyclerView toiletRecyclerView = findViewById(R.id.toiletRecyclerView);
            toiletRecyclerView.setAdapter(_adapter);
            toiletRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            final Runnable backgroundTask = () -> _viewModel.loadToiletsData(restroomId);
            new BasicAsyncTask(backgroundTask, _adapter::notifyDataSetChanged).execute();
        }

        if (restroomId != null) {
            setTitle(restroomLocation);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                _adapter.notifyDataSetChanged();
            }
        }
    }

    public void addNewToilet(View view) {
        Intent intent = new Intent(view.getContext(), AddToiletActivity.class);
        startActivityForResult(intent, 1);
    }
}