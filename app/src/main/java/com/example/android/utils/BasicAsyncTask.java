package com.example.android.utils;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

public class BasicAsyncTask extends AsyncTask<Void, Void, Void> {
    private final Runnable _backgroundTask;
    private final Runnable _postExecute;

    public BasicAsyncTask(@NonNull Runnable backgroundTask, Runnable postExecute) {
        _backgroundTask = backgroundTask;
        _postExecute = postExecute;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        _backgroundTask.run();
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        if (_postExecute != null) {
            _postExecute.run();
        }
    }
}
