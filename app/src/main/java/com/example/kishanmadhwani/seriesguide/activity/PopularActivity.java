package com.example.kishanmadhwani.seriesguide.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.kishanmadhwani.seriesguide.others.ApiClient;
import com.example.kishanmadhwani.seriesguide.R;
import com.example.kishanmadhwani.seriesguide.response.SeriesDetailResponse;
import com.example.kishanmadhwani.seriesguide.response.SeriesResponse;
import com.example.kishanmadhwani.seriesguide.adapter.SeriesAdapter;
import com.example.kishanmadhwani.seriesguide.interfacepackage.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularActivity extends AppCompatActivity {
    private final static String API_KEY = "5eed47bf9f220022611fa37f71fee2f5";
    ProgressDialog progress;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("PopularActivity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        progress = new ProgressDialog(this);
        progress.setTitle("");
        progress.setMessage("");
        progress.setCancelable(false);
        progress.show();
        if (API_KEY.isEmpty()) {
            Toast.makeText(this, "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.popularseries_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<SeriesResponse> call = apiService.popularSeries(API_KEY,"en-US","1","false");
        call.enqueue(new Callback<SeriesResponse>() {
            @Override
            public void onResponse(Call<SeriesResponse> call, Response<SeriesResponse> response) {
                List<SeriesDetailResponse> movies = response.body().getResults();
                recyclerView.setAdapter(new SeriesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
                progress.dismiss();
                Log.d("numberofmovie", "Number of movies received: " + movies.size() + response.body().getResults());
            }

            @Override
            public void onFailure(Call<SeriesResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e("failure", t.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popularmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.addall:
                Toast.makeText(this, "add all", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
