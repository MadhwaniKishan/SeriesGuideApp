package com.example.kishanmadhwani.seriesguide.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.kishanmadhwani.seriesguide.others.ApiClient;
import com.example.kishanmadhwani.seriesguide.response.CastDetailResponse;
import com.example.kishanmadhwani.seriesguide.response.CreditsResponse;
import com.example.kishanmadhwani.seriesguide.response.CrewDetailsResponse;
import com.example.kishanmadhwani.seriesguide.response.GenreDetailResponse;
import com.example.kishanmadhwani.seriesguide.response.MovieDetailResponse;
import com.example.kishanmadhwani.seriesguide.R;
import com.example.kishanmadhwani.seriesguide.adapter.CustomAdapterCastDetail;
import com.example.kishanmadhwani.seriesguide.adapter.CustomAdapterCrewDetail;
import com.example.kishanmadhwani.seriesguide.interfacepackage.ApiInterface;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
* created by : 20122
* Create date: 12-19 -20
* update date:
* Desc:
* @param
* */
public class MovieDetailActivity extends AppCompatActivity {
    private final static String API_KEY = "5eed47bf9f220022611fa37f71fee2f5";
    ProgressDialog progress;
    TextView desc,genres,movietitle,dateruntimetv;
    String overview,title,dateruntime,tmdbrating,traktrating,date,runtime;
    String genre="";
    ImageView iv;
    ListView castlist,crewlist;
    String posterUrl="",baseUrl="https://image.tmdb.org/t/p/w500";
    Button showallCast,showallCrew;
    Bitmap image;
    RelativeLayout moviedetaillayout;
    ArrayList<CrewDetailsResponse> crewDetailResponses =new ArrayList();
    ArrayList<CastDetailResponse> castDetailResponse =new ArrayList();
    Bitmap theBitmap=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        final int id=Integer.parseInt(getIntent().getExtras().get("id").toString());
        progress = new ProgressDialog(this);
        genres=findViewById(R.id.genres);
        progress.setCancelable(false);
        progress.show();
        desc=findViewById(R.id.desc);
        iv=findViewById(R.id.poster);
        movietitle=findViewById(R.id.movietitle);
        dateruntimetv=findViewById(R.id.dateruntime);
        castlist =findViewById(R.id.castlist);
        crewlist=findViewById(R.id.crewlist);
        showallCast=findViewById(R.id.castshowall);
        showallCrew=findViewById(R.id.crewshowall);
        moviedetaillayout=findViewById(R.id.moviedetaillayout);
        showallCrew.setVisibility(View.INVISIBLE);
        showallCast.setVisibility(View.INVISIBLE);
        if (API_KEY.isEmpty()) {
            Toast.makeText(this, "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MovieDetailResponse> call = apiService.movieDetail(id,API_KEY,"en-US","1","false");
        call.enqueue(new Callback<MovieDetailResponse>() {
            @Override
            public void onResponse(Call<MovieDetailResponse> call, Response<MovieDetailResponse> response) {
                title=response.body().getTitle();
                posterUrl=baseUrl+response.body().getPosterPath();
                date=response.body().getReleaseDate();
                runtime=response.body().getRuntime();
                overview = response.body().getOverview()+"\n"+"\n"+"Source: TMDb";
                List<GenreDetailResponse> result=response.body().getGenreDetailResponse();
                for(int i=0;i<result.size();i++){
                    Log.d("genre",result.get(i).getName());
                    genre+=result.get(i).getName()+",";
                }

                DateFormat originalFormat = new SimpleDateFormat("yyyyy-MM-dd", Locale.ENGLISH);
                DateFormat targetFormat = new SimpleDateFormat("d MMMM");
                Date dateChange = null;
                Log.d("formateddate",date);
                try {
                    dateChange = originalFormat.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String formattedDate = targetFormat.format(dateChange);
                Log.d("formateddate",formattedDate);
                dateruntime=formattedDate+" | "+runtime + " min";

                genre=genre.substring(0,genre.length()-1);
                Log.d("genre",response.body().getGenreDetailResponse().toString());
                genres.setText(genre);
                progress.dismiss();
                desc.setText(overview);
                movietitle.setText(title);
                dateruntimetv.setText(dateruntime);


//                Palette p = Palette.from(theBitmap).generate();
//                Palette.Swatch vibrantSwatch = p.getVibrantSwatch();
//                int backgroundColor =vibrantSwatch.getRgb();
//                moviedetaillayout.setBackgroundColor(backgroundColor);
                Glide.with(MovieDetailActivity.this)
                        .asBitmap()
                        .load(posterUrl)
                        .into(new SimpleTarget<Bitmap>(100,100) {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                iv.setImageBitmap(resource);
                                Palette p = Palette.from(resource).generate();
                                List<Palette.Swatch> t= p.getSwatches();
                                for(int i=0;i<t.size();i++){
                                    Log.d("swatcheslist",t.get(i).toString());
                                }
                                Palette.Swatch vibrantSwatch = p.getDarkVibrantSwatch();
                                if(vibrantSwatch!=null) {
                                    int backgroundColor = vibrantSwatch.getRgb();
                                    moviedetaillayout.setBackgroundColor(backgroundColor);
                                }
                                else {
                                    Log.d("checknull","null");
                                    Palette.Swatch muted=p.getDarkMutedSwatch();
                                    if(muted==null){

                                    }
                                    else{
                                        int backgroundColor = muted.getRgb();
                                        moviedetaillayout.setBackgroundColor(backgroundColor);
                                    }
                                }
                            }
                        });
            }

            @Override
            public void onFailure(Call<MovieDetailResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e("failure", t.toString());
            }
        });

        Call<CreditsResponse> callcredits = apiService.creditDetails(id,API_KEY);
        callcredits.enqueue(new Callback<CreditsResponse>() {
            @Override
            public void onResponse(Call<CreditsResponse> call, Response<CreditsResponse> response) {
                castDetailResponse =(ArrayList<CastDetailResponse>) response.body().getCastDetailResponse();
                crewDetailResponses =(ArrayList<CrewDetailsResponse>) response.body().getCrewDetailResponses();
                Resources res =getResources();
                 CustomAdapterCastDetail adaptercast= new CustomAdapterCastDetail(MovieDetailActivity.this, castDetailResponse,res,3);

                    castlist.setAdapter(adaptercast);
                    showallCast.setVisibility(View.VISIBLE);

                   CustomAdapterCrewDetail adaptercrew= new CustomAdapterCrewDetail(MovieDetailActivity.this, crewDetailResponses,res,3);

                    crewlist.setAdapter(adaptercrew);
                    showallCrew.setVisibility(View.VISIBLE);

                castlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position,
                                            long id) {
                        Intent intent = new Intent(MovieDetailActivity.this, PersonDetailActivity.class);
                        intent.putExtra("id", castDetailResponse.get(position).getId());
                        startActivity(intent);
                    }
                });
                crewlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position,
                                            long id) {
                        Intent intent = new Intent(MovieDetailActivity.this, PersonDetailActivity.class);
                        intent.putExtra("id", crewDetailResponses.get(position).getId());
                        startActivity(intent);
                    }
                });


                    showallCast.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i=new Intent(MovieDetailActivity.this,ShowAllCreditsActivity.class);
                            i.putExtra("castlist", castDetailResponse);
                            i.putExtra("credit","castDetailResponse");
                            startActivity(i);
                        }
                    });

                    showallCrew.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i=new Intent(MovieDetailActivity.this,ShowAllCreditsActivity.class);
                            i.putExtra("crewlist", crewDetailResponses);
                            i.putExtra("credit","crewDetailResponses");
                            startActivity(i);
                        }
                    });
            }

            @Override
            public void onFailure(Call<CreditsResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e("failure", t.toString());
            }
        });



    }
}
