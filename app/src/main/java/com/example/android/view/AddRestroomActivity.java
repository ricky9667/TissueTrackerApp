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
    private final Store store = Store.getInstance();
    private EditText restroomLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_restroom_activity);
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
}