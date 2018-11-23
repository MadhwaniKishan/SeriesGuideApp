package com.example.kishanmadhwani.seriesguide.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.kishanmadhwani.seriesguide.others.ApiClient;
import com.example.kishanmadhwani.seriesguide.activity.DiscMoviesActivity;
import com.example.kishanmadhwani.seriesguide.response.MovieBasicDetailResponse;
import com.example.kishanmadhwani.seriesguide.response.MovieResponse;
import com.example.kishanmadhwani.seriesguide.adapter.MoviesAdapter;
import com.example.kishanmadhwani.seriesguide.activity.PopularMoviesActivity;
import com.example.kishanmadhwani.seriesguide.R;
import com.example.kishanmadhwani.seriesguide.activity.DigitalMoviesActivity;
import com.example.kishanmadhwani.seriesguide.interfacepackage.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesDiscoverFragment extends Fragment  {

    private final static String API_KEY = "5eed47bf9f220022611fa37f71fee2f5";
    ProgressDialog progress;
    Button popular,digital,disc;
    Intent i;
    Context ctx;
    public MoviesDiscoverFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_movies_discover, container, false);
    }
    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.moviemainmenu, menu);
        menu.findItem(R.id.refresh).setVisible(false);
        menu.findItem(R.id.filter).setVisible(false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        popular=view.findViewById(R.id.popular);
        disc=view.findViewById(R.id.disc);
        digital=view.findViewById(R.id.digital);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.theater_recycler_view);
        progress = new ProgressDialog(getContext());
        progress.setTitle("");
        progress.setMessage("");
        progress.setCancelable(false);
        progress.show();




        popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=new Intent(getContext(),PopularMoviesActivity.class);
                startActivity(i);
            }
        });

        disc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=new Intent(getContext(),DiscMoviesActivity.class);
                startActivity(i);
            }
        });
        digital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=new Intent(getContext(),DigitalMoviesActivity.class);
                startActivity(i);
            }
        });

        if (API_KEY.isEmpty()) {
            Toast.makeText(getContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }


        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.discoverMovies(API_KEY,"en-US","1","false");
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<MovieBasicDetailResponse> movieBasicDetailResponses = response.body().getResults();
                recyclerView.setAdapter(new MoviesAdapter(movieBasicDetailResponses, R.layout.grid_item,getActivity()));
                progress.dismiss();
                Log.d("numberofmovie", "Number of movieBasicDetailResponses received: " + movieBasicDetailResponses.size() + response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e("failure", t.toString());
            }
        });


    }


}
