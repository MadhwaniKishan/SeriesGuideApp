package com.example.kishanmadhwani.seriesguide;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;


public class ShowsFragment extends Fragment {
 Toolbar toolbar;
 FloatingActionButton floating;
    public ShowsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_shows, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //super.onViewCreated(view, savedInstanceState);
      floating=view.findViewById(R.id.fab);
      floating.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent i =new Intent(getContext(),AddNewShows.class);
            startActivity(i);
          }
      });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.option, menu);
        menu.findItem(R.id.refresh).setVisible(false);
        menu.findItem(R.id.cal).setVisible(false);

    }
}
