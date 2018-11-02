package com.example.kishanmadhwani.seriesguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesCollectionFragment extends Fragment {


    public MoviesCollectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_movies_collection, container, false);
    }
    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.moviemainmenu, menu);
        menu.findItem(R.id.refresh).setVisible(false);
        menu.findItem(R.id.language).setVisible(false);
    }

}
