package com.example.android.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.R;
import com.example.android.model.Restroom;
import com.example.android.service.Store;

public class AddRestroomActivity extends AppCompatActivity {
    private final Store store = Store.getInstance();
    private EditText restroomIdEditText;
    private EditText restroomLocationEditText;

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