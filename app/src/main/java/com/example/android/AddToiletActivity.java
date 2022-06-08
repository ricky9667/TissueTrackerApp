package com.example.android;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddToiletActivity extends AppCompatActivity {
    public EditText toiletIdEditText;
    public EditText toiletLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_toilet_activity);
    }

    public void submitNewToilet(View view) {
        toiletIdEditText = view.findViewById(R.id.toiletIdEditText);
        toiletLocationEditText = view.findViewById(R.id.toiletLocationEditText);
        finishActivity(1);
    }
}