package com.example.kishanmadhwani.seriesguide.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.example.kishanmadhwani.seriesguide.others.ApiClient;
import com.example.kishanmadhwani.seriesguide.response.MovieBasicDetailResponse;
import com.example.kishanmadhwani.seriesguide.response.MovieResponse;
import com.example.kishanmadhwani.seriesguide.R;
import com.example.kishanmadhwani.seriesguide.adapter.MoviesAdapter;
import com.example.kishanmadhwani.seriesguide.interfacepackage.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PopularMoviesActivity extends AppCompatActivity {
    private final static String API_KEY = "5eed47bf9f220022611fa37f71fee2f5";
    Toolbar toolbar;
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movies);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("PopularActivity");

        progress = new ProgressDialog(this);
        progress.setTitle("");
        progress.setMessage("");
        progress.setCancelable(false);
        progress.show();
        if (API_KEY.isEmpty()) {
            Toast.makeText(this, "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.popularmovies_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.popularMovies(API_KEY,"en-US","1","false");
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<MovieBasicDetailResponse> movieBasicDetailResponses = response.body().getResults();
                recyclerView.setAdapter(new MoviesAdapter(movieBasicDetailResponses, R.layout.grid_item, PopularMoviesActivity.this));
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
