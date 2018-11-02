package com.example.kishanmadhwani.seriesguide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder> {

    private List<Series> movies;
    private int rowLayout;
    private Context context;
    String baseUrl="https://image.tmdb.org/t/p/w500",posterUrl;

    public static class SeriesViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;
        ImageView iv;

        public SeriesViewHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
//            data = (TextView) v.findViewById(R.id.subtitle);
            movieDescription = (TextView) v.findViewById(R.id.description);
            iv=v.findViewById(R.id.poster);
            //rating = (TextView) v.findViewById(R.id.rating);
        }
    }
    public SeriesAdapter(List<Series> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public SeriesAdapter.SeriesViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new SeriesViewHolder(view);
    }


    @Override
    public void onBindViewHolder(SeriesViewHolder holder, final int position) {
        holder.movieTitle.setText(movies.get(position).getTitle());
       // holder.data.setText(movies.get(position).getReleaseDate());
        holder.movieDescription.setText(movies.get(position).getOverview());
         posterUrl=baseUrl+movies.get(position).getPosterPath();


        Glide.with(context)
                .load(posterUrl)  //url of image
                .into(holder.iv);

     //   holder.rating.setText(movies.get(position).getVoteAverage().toString());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
