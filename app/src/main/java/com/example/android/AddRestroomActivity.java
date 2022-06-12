package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.android.models.Restroom;

public class AddRestroomActivity extends AppCompatActivity {
    private EditText restroomIdEditText;
    private EditText restroomLocationEditText;
    private final Store store = Store.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_restroom_activity);
        restroomIdEditText = findViewById(R.id.restroomIdEditText);
        restroomLocationEditText = findViewById(R.id.restroomLocationEditText);
    }

    public void submitNewRestroom(View view) {
        Intent replyIntent = new Intent();
        final String restroomId = restroomIdEditText.getText().toString();
        final String restroomLocation = restroomLocationEditText.getText().toString();
        store.addRestroom(new Restroom(restroomId, restroomLocation));
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}