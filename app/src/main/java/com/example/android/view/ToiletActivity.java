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
import com.example.android.model.Restroom;
import com.example.android.service.Store;
import com.example.android.viewModel.ToiletsViewModel;

public class ToiletActivity extends AppCompatActivity {
    private final Store _store = Store.getInstance();
    private ToiletsViewModel _viewModel;
    private Restroom _restroom = null;
    private RecyclerView _toiletRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toilet_activity);

        int restroomIndex = _store.getShowingRestroomIndex();
        if (restroomIndex != -1) {
            _restroom = _store.getRestroom(restroomIndex);
            setTitle(_restroom.getLocation());
        }

        ToiletListAdapter adapter = new ToiletListAdapter(this);
        _viewModel = new ToiletsViewModel(adapter);

        _toiletRecyclerView = findViewById(R.id.toiletRecyclerView);
        _toiletRecyclerView.setAdapter(adapter);
        _toiletRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        final Runnable backgroundTask = () -> _viewModel.loadToiletsData(_restroom.getId());
        new BasicAsyncTask(backgroundTask, adapter::notifyDataSetChanged).execute();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                _toiletRecyclerView.getAdapter().notifyDataSetChanged();
            }
        }
    }

    public void addNewToilet(View view) {
        Intent intent = new Intent(view.getContext(), AddToiletActivity.class);
        startActivityForResult(intent, 1);
    }
}