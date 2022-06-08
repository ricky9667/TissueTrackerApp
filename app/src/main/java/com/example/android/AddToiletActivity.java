package com.example.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddToiletActivity extends AppCompatActivity {
    public static final String EXTRA_TOILET_ID = "com.example.android.TissueTrackerApp.extra.REPLY.ToiletId";
    public static final String EXTRA_TOILET_LOCATION = "com.example.android.TissueTrackerApp.extra.REPLY.ToiletLocation";
    public EditText toiletIdEditText;
    public EditText toiletLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_toilet_activity);
        toiletIdEditText = findViewById(R.id.toiletIdEditText);
        toiletLocationEditText = findViewById(R.id.toiletLocationEditText);
    }

    public void submitNewToilet(View view) {
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_TOILET_ID, toiletIdEditText.getText().toString());
        replyIntent.putExtra(EXTRA_TOILET_LOCATION, toiletLocationEditText.getText().toString());
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}