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

        new BasicAsyncTask(() -> _viewModel.loadUndeployedIds(), adapter::notifyDataSetChanged).execute();

        toiletIdSpinner = findViewById(R.id.toiletIdSpinner);
        toiletIdSpinner.setAdapter(adapter);
        toiletLocationEditText = findViewById(R.id.toiletLocationEditText);
    }

    public void submitNewToilet(View view) {
//        Intent replyIntent = new Intent();
//        final String toiletId = toiletIdEditText.getText().toString();
//        final String toiletLocation = toiletLocationEditText.getText().toString();
//        final double percentage = tissueAmountSeekBar.getProgress() / 100.0f;
//
//        if (toiletState == ToiletState.SUFFICIENT) {
//            toiletState = percentage >= 0.1f ? ToiletState.SUFFICIENT : ToiletState.INSUFFICIENT;
//        }
//
//        int restroomIndex = store.getShowingRestroomIndex();
//        store.addToilet(restroomIndex, new Toilet(toiletId, toiletLocation, toiletState, percentage));
//        setResult(RESULT_OK, replyIntent);
//        finish();
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
