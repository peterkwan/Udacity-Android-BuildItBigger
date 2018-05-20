package org.peterkwan.udacity.jokedisplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DisplayJokeActivity extends AppCompatActivity {

    private static final String JOKE = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            String joke = intent.getStringExtra(JOKE);

            Bundle args = new Bundle();
            args.putString(JOKE, joke);

            DisplayJokeFragment fragment = new DisplayJokeFragment();
            fragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.jokeFragment, fragment)
                    .commit();
        }
    }
}
