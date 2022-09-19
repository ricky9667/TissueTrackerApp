package com.example.android.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.R;
import com.example.android.model.ToiletState;
import com.example.android.utils.BasicAsyncTask;
import com.example.android.viewModel.ToiletInfoViewModel;

import java.util.ArrayList;

public class AddToiletActivity extends AppCompatActivity {
    private ToiletInfoViewModel _viewModel;
    private Spinner _toiletIdSpinner;
    private EditText _toiletLocationEditText;
    private ToiletState toiletState = ToiletState.SUFFICIENT;
    private String _restroomId;
    private String _restroomLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_toilet_activity);

        Intent intent = getIntent();
        _restroomId = intent.getStringExtra("restroomId");
        _restroomLocation = intent.getStringExtra("restroomLocation");

        ArrayList<String> arrayList = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrayList);
        _viewModel = new ToiletInfoViewModel(arrayList);

        final Runnable backgroundTask = () -> _viewModel.loadUndeployedIds();
        new BasicAsyncTask(backgroundTask, adapter::notifyDataSetChanged).execute();

        _toiletIdSpinner = findViewById(R.id.toiletIdSpinner);
        _toiletIdSpinner.setAdapter(adapter);
        _toiletLocationEditText = findViewById(R.id.toiletLocationEditText);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent();
        intent.putExtra("restroomId", _restroomId);
        intent.putExtra("restroomLocation", _restroomLocation);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void submitNewToilet(View view) {
        Intent intent = new Intent();

        final String toiletId = _toiletIdSpinner.getSelectedItem().toString();
        final String toiletLocation = _toiletLocationEditText.getText().toString();

        final Runnable backgroundTask = () -> _viewModel.registerToilet(_restroomId, toiletId, toiletLocation);
        new BasicAsyncTask(backgroundTask, null).execute();

        intent.putExtra("restroomId", _restroomId);
        intent.putExtra("restroomLocation", _restroomLocation);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onToiletStateChange(View view) {
        switch (view.getId()) {
            case R.id.toiletStateNormal:
                toiletState = ToiletState.SUFFICIENT;
                break;
            case R.id.toiletStateCleaning:
                toiletState = ToiletState.CLEANING;
                break;
            case R.id.toiletStateDisconnected:
                toiletState = ToiletState.DISCONNECTED;
                break;
        }
    }
}