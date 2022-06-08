package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddRestroomActivity extends AppCompatActivity {
    public EditText restroomIdEditText;
    public EditText restroomLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_restroom_activity);
    }

    public void submitNewRestroom(View view) {
        restroomIdEditText = view.findViewById(R.id.restroomIdEditText);
        restroomLocationEditText = view.findViewById(R.id.restroomLocationEditText);
        finishActivity(1);
    }
}