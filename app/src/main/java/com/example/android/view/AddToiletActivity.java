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
    private Spinner toiletIdSpinner;
    private EditText toiletLocationEditText;
    private ToiletState toiletState = ToiletState.SUFFICIENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_toilet_activity);

        ArrayList<String> arrayList = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrayList);
        _viewModel = new ToiletInfoViewModel(arrayList);

        final Runnable backgroundTask = () -> _viewModel.loadUndeployedIds();
        new BasicAsyncTask(backgroundTask, adapter::notifyDataSetChanged).execute();

        toiletIdSpinner = findViewById(R.id.toiletIdSpinner);
        toiletIdSpinner.setAdapter(adapter);
        toiletLocationEditText = findViewById(R.id.toiletLocationEditText);
    }

    public void submitNewToilet(View view) {
        Intent replyIntent = new Intent();

        final String restroomId = "1"; // debug
        final String toiletId = toiletIdSpinner.getSelectedItem().toString();
        final String toiletLocation = toiletLocationEditText.getText().toString();

        final Runnable backgroundTask = () -> _viewModel.registerToilet(restroomId, toiletId, toiletLocation);
        new BasicAsyncTask(backgroundTask, null).execute();

        setResult(RESULT_OK, replyIntent);
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