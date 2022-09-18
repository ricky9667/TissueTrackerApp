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
    private String _restroomId;
    private String _restroomLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toilet_activity);

        initActivityInformation();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                initActivityInformation();
            }
        }
    }

    private void initActivityInformation() {
        Intent intent = getIntent();
        _restroomId = intent.getStringExtra("restroomId");
        _restroomLocation = intent.getStringExtra("restroomLocation");

        if (_restroomId != null) {
            ToiletListAdapter _adapter = new ToiletListAdapter(this);
            _viewModel = new ToiletsViewModel(_adapter);

            RecyclerView toiletRecyclerView = findViewById(R.id.toiletRecyclerView);
            toiletRecyclerView.setAdapter(_adapter);
            toiletRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            final Runnable backgroundTask = () -> _viewModel.loadToiletsData(_restroomId);
            new BasicAsyncTask(backgroundTask, _adapter::notifyDataSetChanged).execute();
        }

        if (_restroomLocation != null) {
            setTitle(_restroomLocation);
        }
    }

    public void addNewToilet(View view) {
        Intent intent = new Intent(view.getContext(), AddToiletActivity.class);
        intent.putExtra("restroomId", _restroomId);
        intent.putExtra("restroomLocation", _restroomLocation);
        startActivityForResult(intent, 1);
    }
}