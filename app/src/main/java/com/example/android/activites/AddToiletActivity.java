package com.example.android.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.R;
import com.example.android.classes.Toilet;
import com.example.android.classes.ToiletState;
import com.example.android.store.Store;

public class AddToiletActivity extends AppCompatActivity {
    private final Store store = Store.getInstance();
    private EditText toiletIdEditText;
    private EditText toiletLocationEditText;

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