package com.example.kishanmadhwani.seriesguide.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kishanmadhwani.seriesguide.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticsMainFragment extends Fragment {


    public StatisticsMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistics_main, container, false);
    }

}
