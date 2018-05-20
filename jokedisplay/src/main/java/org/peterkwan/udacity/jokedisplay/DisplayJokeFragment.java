package org.peterkwan.udacity.jokedisplay;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayJokeFragment extends Fragment {

    private static final String JOKE = "joke";

    public DisplayJokeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_display_joke, container, false);

        Bundle args = getArguments();
        if (args != null && args.containsKey(JOKE)) {
            TextView textView = rootView.findViewById(R.id.jokeTextView);
            textView.setText(args.getString(JOKE));
        }

        return rootView;
    }

}
