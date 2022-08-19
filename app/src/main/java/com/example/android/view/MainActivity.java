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
    private RecyclerView _restroomRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RestroomListAdapter adapter = new RestroomListAdapter(this);
        _viewModel = new RestroomsViewModel(adapter);

        _restroomRecyclerView = findViewById(R.id.recyclerView);
        _restroomRecyclerView.setAdapter(adapter);
        _restroomRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        new BasicAsyncTask(() -> _viewModel.loadRestroomsData(), () -> adapter.notifyDataSetChanged()).execute();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                _restroomRecyclerView.getAdapter().notifyDataSetChanged();
            }
        }
    }

    public void addNewRestroom(View view) {
        Intent intent = new Intent(view.getContext(), AddRestroomActivity.class);
        startActivityForResult(intent, 1);
    }
}
