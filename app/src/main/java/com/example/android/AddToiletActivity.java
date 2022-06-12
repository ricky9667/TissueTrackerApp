package com.example.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.models.Toilet;
import com.example.android.models.ToiletState;

public class AddToiletActivity extends AppCompatActivity {
    private EditText toiletIdEditText;
    private EditText toiletLocationEditText;
    private final Store store = Store.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_toilet_activity);
        toiletIdEditText = findViewById(R.id.toiletIdEditText);
        toiletLocationEditText = findViewById(R.id.toiletLocationEditText);
    }

    public void submitNewToilet(View view) {
        Intent replyIntent = new Intent();
        final String toiletId = toiletIdEditText.getText().toString();
        final String toiletLocation = toiletLocationEditText.getText().toString();
        int restroomIndex = store.getShowingRestroomIndex();
        store.addToilet(restroomIndex, new Toilet(toiletId, toiletLocation, ToiletState.DISCONNECTED, -1.0f));
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}