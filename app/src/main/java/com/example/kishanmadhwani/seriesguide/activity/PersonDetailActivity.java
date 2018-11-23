package com.example.kishanmadhwani.seriesguide.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kishanmadhwani.seriesguide.others.ApiClient;
import com.example.kishanmadhwani.seriesguide.response.PersonDetailResponse;
import com.example.kishanmadhwani.seriesguide.R;
import com.example.kishanmadhwani.seriesguide.interfacepackage.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonDetailActivity extends AppCompatActivity {
    private final static String API_KEY = "5eed47bf9f220022611fa37f71fee2f5";
    ProgressDialog progress;
    String personname,bio,posterUrl,baseUrl="https://image.tmdb.org/t/p/original";
    TextView personnametv,biotv;
    ImageView personphoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.show();
        personnametv=findViewById(R.id.personname);
        biotv=findViewById(R.id.bio);
        personphoto=findViewById(R.id.personimage);
        Intent i=getIntent();
        String id=i.getExtras().get("id").toString();
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<PersonDetailResponse> call = apiService.getPersonDetail(Integer.parseInt(id),API_KEY,"en-US");
        call.enqueue(new Callback<PersonDetailResponse>() {
            @Override
            public void onResponse(Call<PersonDetailResponse> call, Response<PersonDetailResponse> response) {
                progress.dismiss();
                personname = response.body().getName();
                posterUrl = baseUrl + response.body().getPosterPath();
                bio=response.body().getBio()+"\n"+"\n"+"Source: TMDb";
                personnametv.setText(personname);
                biotv.setText(bio);
                Glide.with(PersonDetailActivity.this)
                        .load(posterUrl)
                        .into(personphoto);
            }
            @Override
            public void onFailure(Call<PersonDetailResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e("failure", t.toString());
            }
        });
    }
}
