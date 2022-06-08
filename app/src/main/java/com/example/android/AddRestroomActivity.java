package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddRestroomActivity extends AppCompatActivity {
    public static final String EXTRA_RESTROOM_ID = "com.example.android.TissueTrackerApp.extra.REPLY.RestroomId";
    public static final String EXTRA_RESTROOM_LOCATION = "com.example.android.TissueTrackerApp.extra.REPLY.RestroomLocation";
    public EditText restroomIdEditText;
    public EditText restroomLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_restroom_activity);
        restroomIdEditText = findViewById(R.id.restroomIdEditText);
        restroomLocationEditText = findViewById(R.id.restroomLocationEditText);
    }

    public void submitNewRestroom(View view) {
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_RESTROOM_ID, restroomIdEditText.getText().toString());
        replyIntent.putExtra(EXTRA_RESTROOM_LOCATION, restroomLocationEditText.getText().toString());
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}