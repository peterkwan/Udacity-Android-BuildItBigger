package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class FetchJokeAsyncTask extends AsyncTask<Void, Void, String> {

    private MyApi apiService;
    private FetchJokeListener listener;
    private FetchJokeProgressListener progressListener;

    public FetchJokeAsyncTask(FetchJokeListener listener, FetchJokeProgressListener progressListener) {
        apiService = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                // options for running against local devappserver
                // - 10.0.2.2 is localhost's IP address in Android emulator
                // - turn off compression when running against local devappserver
                .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                }).build();
        this.listener = listener;
        this.progressListener = progressListener;
    }

    @Override
    protected void onPreExecute() {
        if (progressListener != null)
            progressListener.onTaskStarting();
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            return apiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        if (progressListener != null)
            progressListener.onTaskCompleted();

        if (listener != null)
            listener.onJokeFetched(s);
    }

    public interface FetchJokeListener {
        public void onJokeFetched(String joke);
    }
}
