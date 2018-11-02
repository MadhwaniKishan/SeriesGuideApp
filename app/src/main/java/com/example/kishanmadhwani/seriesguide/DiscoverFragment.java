package com.example.kishanmadhwani.seriesguide;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment {
    private final static String API_KEY = "5eed47bf9f220022611fa37f71fee2f5";
    EditText search;
    Button popular;
    ProgressDialog progress;
    public DiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        search=getActivity().findViewById(R.id.search);
//        search.setHint("Type a show name");
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        popular=view.findViewById(R.id.popular);
        popular.setVisibility(View.INVISIBLE);
         progress = new ProgressDialog(getContext());
         progress.setTitle("");
         progress.setMessage("");
        progress.setCancelable(false);
        progress.show();


        popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),Popular.class);
                startActivity(i);
            }
        });


        if (API_KEY.isEmpty()) {
            Toast.makeText(getContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<SeriesResponse> call = apiService.discoverSeries(API_KEY,"en-US","1","false");
        call.enqueue(new Callback<SeriesResponse>() {
            @Override
            public void onResponse(Call<SeriesResponse> call, Response<SeriesResponse> response) {
                List<Series> movies = response.body().getResults();
                recyclerView.setAdapter(new SeriesAdapter(movies, R.layout.list_item_movie, getContext()));
                progress.dismiss();
                popular.setVisibility(View.VISIBLE);
                Log.d("numberofmovie", "Number of movies received: " + movies.size() + response.body().getResults());
            }

            @Override
            public void onFailure(Call<SeriesResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e("failure", t.toString());
            }
        });

    }
}
