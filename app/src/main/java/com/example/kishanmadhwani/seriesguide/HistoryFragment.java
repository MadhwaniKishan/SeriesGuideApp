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
import android.widget.Toolbar;


public class HistoryFragment extends Fragment {
    Toolbar toolbar;
    public HistoryFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_history, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar=view.findViewById(R.id.toolbar);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.option, menu);
        menu.findItem(R.id.filter).setVisible(false);
        menu.findItem(R.id.sort).setVisible(false);
        menu.findItem(R.id.cal).setVisible(false);
        menu.findItem(R.id.add).setVisible(false);
    }
}
