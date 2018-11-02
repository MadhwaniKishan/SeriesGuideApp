package com.example.kishanmadhwani.seriesguide;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
     static List<Movie> movies;
    private int rowLayout;
    private Context context;
   // private RecyclerViewClickListener mListener;
    String baseUrl="https://image.tmdb.org/t/p/w500",posterUrl;

    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        LinearLayout moviesLayout;
        TextView movieTitle;
        //private RecyclerViewClickListener mListener;
        TextView date;
        ImageView iv;
        Button popup1;
        Context ctx;

        public MovieViewHolder(View v,Context ctx) {
            super(v);
                v.setOnClickListener(this);
                moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
                movieTitle = (TextView) v.findViewById(R.id.title);
//            data = (TextView) v.findViewById(R.id.subtitle);
                date = (TextView) v.findViewById(R.id.date);
                iv = v.findViewById(R.id.poster);
                popup1=v.findViewById(R.id.popup);
                this.ctx=ctx;
            //rating = (TextView) v.findViewById(R.id.rating);
        }
        @Override
        public void onClick(View view) {
          int pos=getAdapterPosition();
          String id=movies.get(pos).getId();
          Intent i=new Intent(ctx,MovieDetail.class);
          i.putExtra("id",id);
          this.ctx.startActivity(i);
         Log.d("clickedyes","clickecd "+movies.get(pos).getId());
        }
    }
    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
       // mListener=listener;
    }


    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view;
//        parent.ge
//        if(viewType==0){
//             view = LayoutInflater.from(parent.getContext()).inflate( R.layout.moviebutton, parent, false);
//        }
//        else
         view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MoviesAdapter.MovieViewHolder(view,context);
    }


    @Override
    public void onBindViewHolder(final MoviesAdapter.MovieViewHolder holder, final int position) {


        holder.popup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, holder.popup1);
                //inflating menu from xml resource
                popup.inflate(R.menu.moviemenu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.addtocollection:
                                Toast.makeText(context,"add to collection",Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.addtowatchlist:
                                Toast.makeText(context,"add to watchlist",Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                //displaying the popup
                popup.show();

            }
        });




        DateFormat originalFormat = new SimpleDateFormat("yyyyy-MM-dd", Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = originalFormat.parse(movies.get(position).getReleaseDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = targetFormat.format(date);
            holder.movieTitle.setText(movies.get(position).getTitle());
            posterUrl = baseUrl + movies.get(position).getPosterPath();
            holder.date.setText(formattedDate);
            Glide.with(context)
                    .load(posterUrl)
                    //.apply(new RequestOptions().override(150, 200))
                    .into(holder.iv)
            ;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
