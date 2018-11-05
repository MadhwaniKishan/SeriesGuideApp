package com.example.kishanmadhwani.seriesguide;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WatchListAdapter extends RecyclerView.Adapter<WatchListAdapter.WatchListViewHolder> {
private int rowLayout;
private Context context;
    Bitmap poster;
ArrayList<WatchList> watchlist=new ArrayList<>();
public static class WatchListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    LinearLayout moviesLayout;
    TextView movieTitle;
    //private RecyclerViewClickListener mListener;
    TextView date;
    Button popup1;
    Context ctx;
    ImageView iv;


    public WatchListViewHolder(View v,Context ctx) {
        super(v);
        v.setOnClickListener(this);
        moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
        movieTitle = (TextView) v.findViewById(R.id.title);
        date = (TextView) v.findViewById(R.id.date);
        iv = v.findViewById(R.id.poster);
        popup1=v.findViewById(R.id.popup);
        this.ctx=ctx;
    }
    @Override
    public void onClick(View view) {
        int pos=getAdapterPosition();
        String id="getid";
        Intent i=new Intent(ctx,MovieDetail.class);
        i.putExtra("id",id);
        this.ctx.startActivity(i);
    }
}
    public WatchListAdapter(ArrayList<WatchList> watchlist, int rowLayout, Context context) {
        this.rowLayout = rowLayout;
        this.context = context;
        this.watchlist=watchlist;
        // mListener=listener;
    }
    @Override
    public WatchListAdapter.WatchListViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new WatchListAdapter.WatchListViewHolder(view,context);
    }


    @Override
    public void onBindViewHolder(final WatchListAdapter.WatchListViewHolder holder, final int position) {
        DateFormat originalFormat = new SimpleDateFormat("yyyyy-MM-dd", Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate="";
        Date date = null;
        try {
            date = originalFormat.parse(watchlist.get(position).getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(date!=null)
        formattedDate = targetFormat.format(date);
        holder.movieTitle.setText(watchlist.get(position).getTitle());
        Log.d("movietitle",watchlist.get(position).getTitle());
          holder.date.setText(formattedDate);
       poster=loadImageFromStorage(watchlist.get(position).getPosterpath(),watchlist.get(position).getPosterpath().substring(1));
        Glide.with(context)
                .load(poster)
                .into(holder.iv)
        ;
    }

    @Override
    public int getItemCount() {
        return watchlist.size();
    }
    private Bitmap loadImageFromStorage(String path,String name)
    {
        Bitmap b=null;
        try {
            File f=new File(path, "profile.jpg");
            Log.d("path",path);
            b= BitmapFactory.decodeStream(new FileInputStream(f));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return  b;
    }
}
