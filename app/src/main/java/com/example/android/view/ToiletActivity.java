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

import java.util.Timer;
import java.util.TimerTask;

public class ToiletActivity extends AppCompatActivity {
    private ToiletsViewModel _viewModel;
    private final Timer _timer = new Timer();

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        _timer.cancel();
    }

    private void initActivityInformation() {
        Intent intent = getIntent();
        String restroomId = intent.getStringExtra("restroomId");
        String restroomLocation = intent.getStringExtra("restroomLocation");

        if (restroomId != null && restroomLocation != null) {
            setTitle(restroomLocation);

            ToiletListAdapter adapter = new ToiletListAdapter(this);
            _viewModel = new ToiletsViewModel(restroomId, restroomLocation, adapter);

            RecyclerView toiletRecyclerView = findViewById(R.id.toiletRecyclerView);
            toiletRecyclerView.setAdapter(adapter);
            toiletRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            final Runnable backgroundTask = () -> _viewModel.loadToiletsData();
            new BasicAsyncTask(backgroundTask, adapter::notifyDataSetChanged).execute();

            _timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    new BasicAsyncTask(backgroundTask, adapter::notifyDataSetChanged).execute();
                }
            }, 0, 5000);
        }
    }

    public void addNewToilet(View view) {
        Intent intent = new Intent(view.getContext(), AddToiletActivity.class);
        intent.putExtra("restroomId", _viewModel.getRestroomId());
        intent.putExtra("restroomLocation", _viewModel.getRestroomLocation());
        startActivityForResult(intent, 1);
    }
}
