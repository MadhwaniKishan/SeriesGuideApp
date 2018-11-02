package com.example.kishanmadhwani.seriesguide;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddNewShowsFragment extends Fragment {
    EditText search;

    public AddNewShowsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_add_new_shows, container, false);
    }

    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.discovershowsmenu, menu);
        menu.findItem(R.id.clear).setVisible(false);
        menu.findItem(R.id.language).setVisible(false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
