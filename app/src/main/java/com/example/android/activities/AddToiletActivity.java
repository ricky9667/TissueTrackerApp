package com.example.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.R;
import com.example.android.classes.Toilet;
import com.example.android.classes.ToiletState;
import com.example.android.store.Store;

public class AddToiletActivity extends AppCompatActivity {
    private final Store store = Store.getInstance();
    private EditText toiletIdEditText;
    private EditText toiletLocationEditText;
    private TextView tissueAmountTextView;
    private SeekBar tissueAmountSeekBar;
    private ToiletState toiletState = ToiletState.SUFFICIENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_toilet_activity);
        toiletIdEditText = findViewById(R.id.toiletIdEditText);
        toiletLocationEditText = findViewById(R.id.toiletLocationEditText);
        tissueAmountTextView = findViewById(R.id.tissueAmountTextView);
        tissueAmountSeekBar = findViewById(R.id.tissueAmountSeekBar);
        tissueAmountSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tissueAmountTextView.setText("Tissue amount: " + i + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    public void submitNewToilet(View view) {
        Intent replyIntent = new Intent();
        final String toiletId = toiletIdEditText.getText().toString();
        final String toiletLocation = toiletLocationEditText.getText().toString();
        final double percentage = tissueAmountSeekBar.getProgress() / 100.0f;

        if (toiletState == ToiletState.SUFFICIENT) {
            toiletState = percentage >= 0.1f ? ToiletState.SUFFICIENT : ToiletState.INSUFFICIENT;
        }

        int restroomIndex = store.getShowingRestroomIndex();
        store.addToilet(restroomIndex, new Toilet(toiletId, toiletLocation, toiletState, percentage));
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