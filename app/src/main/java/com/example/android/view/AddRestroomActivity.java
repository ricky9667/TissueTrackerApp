package com.example.android.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.R;
import com.example.android.service.Store;
import com.example.android.utils.BasicAsyncTask;
import com.example.android.viewModel.RestroomInfoViewModel;

public class AddRestroomActivity extends AppCompatActivity {
    private RestroomInfoViewModel _viewModel;
    private EditText restroomIdEditText;
    private EditText restroomLocationEditText;
    private static final String RESTROOM_INTENT_FLAG = "restroomIntentFlag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String restroomIntentFlag = intent.getStringExtra(RESTROOM_INTENT_FLAG);
        if (restroomIntentFlag.equals("register")) {
            setContentView(R.layout.add_restroom_activity);
        } else if (restroomIntentFlag.equals("update")) {
            setContentView(R.layout.update_restroom_activity);
            restroomIdEditText = findViewById(R.id.restroomIdEditText);
        }
        restroomLocationEditText = findViewById(R.id.restroomLocationEditText);

        _viewModel = new RestroomInfoViewModel();
    }

    public void submitNewRestroom(View view) {
        Intent replyIntent = new Intent();
        final String restroomLocation = restroomLocationEditText.getText().toString();

        final Runnable backgroundTask = () -> _viewModel.registerRestroom(restroomLocation);
        new BasicAsyncTask(backgroundTask, null).execute();

        setResult(RESULT_OK, replyIntent);
        finish();
    }

    public void updateRestroomLocation(View view) {
        Intent replyIntent = new Intent();
        final String restroomIdText = restroomIdEditText.getText().toString();
        final String restroomLocation = restroomLocationEditText.getText().toString();
        final int restroomId = Integer.parseInt(restroomIdText);

        final Runnable backgroundTask = () -> _viewModel.updateRestroomLocation(restroomId, restroomLocation);
        new BasicAsyncTask(backgroundTask, null).execute();

        setResult(RESULT_OK, replyIntent);
        finish();
    }
}