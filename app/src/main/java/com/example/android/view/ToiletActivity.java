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
    private final String RESTROOM_ID_EXTRA = "restroomIdExtra";
    private final String RESTROOM_LOCATION_EXTRA = "restroomLocationExtra";
    private ToiletsViewModel _viewModel;
    private Restroom _restroom = null;
    private RecyclerView _toiletRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toilet_activity);
        String restroomId = getIntent().getStringExtra(RESTROOM_ID_EXTRA);
        String restroomLocationTitle = getIntent().getStringExtra(RESTROOM_LOCATION_EXTRA);
        setTitle(restroomLocationTitle);

        if (!restroomId.equals("")){
            _restroom = new Restroom(restroomId, restroomLocationTitle);
            ToiletListAdapter adapter = new ToiletListAdapter(this);
            _viewModel = new ToiletsViewModel(adapter);

            _toiletRecyclerView = findViewById(R.id.toiletRecyclerView);
            _toiletRecyclerView.setAdapter(adapter);
            _toiletRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            final Runnable backgroundTask = () -> _viewModel.loadToiletsData(_restroom.getId());
            new BasicAsyncTask(backgroundTask, adapter::notifyDataSetChanged).execute();
        }
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