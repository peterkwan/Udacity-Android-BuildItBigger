package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.udacity.gradle.builditbigger.FetchJokeAsyncTask.FetchJokeListener;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements FetchJokeListener, FetchJokeProgressListener {

    private TellJokeListener listener;
    private ProgressBar progressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        progressBar = root.findViewById(R.id.progressBar);

        Button tellJokeButton = root.findViewById(R.id.tellJokeButton);
        tellJokeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new FetchJokeAsyncTask(MainActivityFragment.this, MainActivityFragment.this).execute();
            }
        });

        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof TellJokeListener)
            listener = (TellJokeListener) context;
        else
            throw new RuntimeException(context.toString()
                    + " must implement TellJokeListener");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onJokeFetched(String joke) {
        if (listener != null)
            listener.displayJoke(joke);
    }

    @Override
    public void onTaskStarting() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onTaskCompleted() {
        progressBar.setVisibility(View.GONE);
    }

    public interface TellJokeListener {
        void displayJoke(String joke);
    }

}
