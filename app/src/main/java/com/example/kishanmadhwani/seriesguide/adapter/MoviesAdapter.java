package com.example.kishanmadhwani.seriesguide.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.BaseColumns;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kishanmadhwani.seriesguide.response.MovieBasicDetailResponse;
import com.example.kishanmadhwani.seriesguide.activity.MovieDetailActivity;
import com.example.kishanmadhwani.seriesguide.R;
import com.example.kishanmadhwani.seriesguide.database.FeedReaderContract;
import com.example.kishanmadhwani.seriesguide.database.FeedReaderDbHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
     static List<MovieBasicDetailResponse> movieBasicDetailResponses;
    private int rowLayout;
    FeedReaderDbHelper mDbHelper ;
    SQLiteDatabase db;
    private Context context;
    String baseUrl="https://image.tmdb.org/t/p/w500",posterUrl;
    String collectionTitle="Add to collection";
    String watchlistTitle="Add to watchlist";
    int flag=1,flagw=1;
    long row;
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
          String id= movieBasicDetailResponses.get(pos).getId();
          Intent i=new Intent(ctx,MovieDetailActivity.class);
          i.putExtra("id",id);
          this.ctx.startActivity(i);
         Log.d("clickedyes","clickecd "+ movieBasicDetailResponses.get(pos).getId());
        }
    }
    public MoviesAdapter(List<MovieBasicDetailResponse> movieBasicDetailResponses, int rowLayout, Context context) {
        this.movieBasicDetailResponses = movieBasicDetailResponses;
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


    private int queryCollection(int pos){
        flag=0;
        mDbHelper=new FeedReaderDbHelper(context);
        db = mDbHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_COLLECTION
        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { movieBasicDetailResponses.get(pos).getTitle()};

        Cursor c = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,
                selectionArgs,                   // don't group the rows
                null,        // The sort order
                null,
                null
        );
        int collectioncolumn=c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_COLLECTION);
        int collection=0;
        while(c.moveToNext()) {
           collection = c.getInt(collectioncolumn);
            Log.d("collectionwhile",collection+" abc");
        }
        Log.d("collection",collection+" abc");
        if(collection<=0)
            flag=0;
        else if(collection==1||collection==2)
            flag=1;
        if(collection==1)
            return 1;
        if(collection==2)
            return 2;
        return 0;
    }

    private int queryWatchlist(int pos){
        flagw=0;
        mDbHelper=new FeedReaderDbHelper(context);
        db = mDbHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_WATCHLIST
        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { movieBasicDetailResponses.get(pos).getTitle()};

        Cursor c = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,
                selectionArgs,                   // don't group the rows
                null,        // The sort order
                null,
                null
        );
        int watchlistcolumn=c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_WATCHLIST);
        int watchlist=0;
        while(c.moveToNext()) {
            watchlist = c.getInt(watchlistcolumn);
            Log.d("watchlistwhile",watchlist+" abc");
        }
        Log.d("watchlist",watchlist+" abc");
        if(watchlist<=0)
            flagw=0;
        else if(watchlist==1 || watchlist==2)
            flagw=1;
        if(watchlist==1)
            return 1;
        if(watchlist==2)
        return 2;
        return 0;
    }


    @Override
    public void onBindViewHolder(final MoviesAdapter.MovieViewHolder holder, final int position) {




        holder.popup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(context);
                final SQLiteDatabase db = mDbHelper.getWritableDatabase();
                int col=0,wat=0;
                col=queryCollection(position);
                Log.d("col",col+"");
                if(col==2){
                    collectionTitle="Remove from collection";
                    Log.d("titlecollectionremove",collectionTitle);
                }
                else if(col==1 || col==0){
                    collectionTitle = "Add to collection";
                    Log.d("titlecollectionadd",collectionTitle);
                }
                wat=queryWatchlist(position);
                Log.d("wat",wat+"");
                if(wat==2){
                    watchlistTitle="Remove from watchlist";
                    Log.d("titlewatchlistremove",watchlistTitle);
                }
                else if(wat==1 || wat==0){
                    watchlistTitle = "Add to watchlist";
                    Log.d("titlewatchlistadd",watchlistTitle);
                }
                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, holder.popup1);
                //inflating menu from xml resource
                popup.inflate(R.menu.moviemenu);
                final Menu menuOpts = popup.getMenu();
                menuOpts.getItem(1).setTitle(collectionTitle);
                menuOpts.getItem(0).setTitle(watchlistTitle);
                //adding click listener


                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Bitmap poster;
                        ContentValues values;
                        String name,posterpath;
                        ContentValues updateValues;
                        switch (item.getItemId()) {

                            case R.id.addtocollection:
                                poster = ((BitmapDrawable) holder.iv.getDrawable()).getBitmap();
                                name = movieBasicDetailResponses.get(position).getPosterPath().substring(1);
                                posterpath = saveToInternalStorage(poster, name);
                                Log.d("posterpath", posterpath);
                                values = new ContentValues();
                                updateValues=new ContentValues();
                               if(flag==1 || flagw==1) {
                                   if (collectionTitle.equals("Remove from collection")) {
                                       updateValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_COLLECTION, 1);
                                   } else {
                                       updateValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_COLLECTION, 2);
                                   }
                                   row= db.update(FeedReaderContract.FeedEntry.TABLE_NAME,updateValues,FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE+"=?",new String[]{movieBasicDetailResponses.get(position).getTitle()});
                                   Log.d("row",row+"");
                               }
                               else{
                                    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, movieBasicDetailResponses.get(position).getTitle());
                                    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE, movieBasicDetailResponses.get(position).getReleaseDate());
                                    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_POSTERPATH, name);
                                    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_COLLECTION,2);
                                    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_MOVIEID, movieBasicDetailResponses.get(position).getId());
                                    row=db.insert(FeedReaderContract.FeedEntry.TABLE_NAME,null,values);
                                }

                                return true;
                            case R.id.addtowatchlist:
                                poster = ((BitmapDrawable)holder.iv.getDrawable()).getBitmap();
                                name= movieBasicDetailResponses.get(position).getPosterPath().substring(1);
                                posterpath=saveToInternalStorage(poster,name);
                                values = new ContentValues();
                                updateValues=new ContentValues();
                                if(flagw==1 || flag==1) {
                                    if (watchlistTitle.equals("Remove from watchlist")) {
                                        updateValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_WATCHLIST, 1);
                                    } else {
                                        updateValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_WATCHLIST, 2);
                                    }
                                    row= db.update(FeedReaderContract.FeedEntry.TABLE_NAME,updateValues,FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE+"=?",new String[]{movieBasicDetailResponses.get(position).getTitle()});
                                    Log.d("row",row+"");
                                }
                                else{
                                    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, movieBasicDetailResponses.get(position).getTitle());
                                    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE, movieBasicDetailResponses.get(position).getReleaseDate());
                                    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_POSTERPATH, name);
                                    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_WATCHLIST,2);
                                    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_MOVIEID, movieBasicDetailResponses.get(position).getId());
                                    row=db.insert(FeedReaderContract.FeedEntry.TABLE_NAME,null,values);
                                }
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
            date = originalFormat.parse(movieBasicDetailResponses.get(position).getReleaseDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = targetFormat.format(date);
            holder.movieTitle.setText(movieBasicDetailResponses.get(position).getTitle());
            posterUrl = baseUrl + movieBasicDetailResponses.get(position).getPosterPath();
            Log.d("urlpath", movieBasicDetailResponses.get(position).getPosterPath());
            holder.date.setText(formattedDate);
            Glide.with(context)
                    .load(posterUrl)
                    .into(holder.iv)
            ;
    }

    @Override
    public int getItemCount() {
        return movieBasicDetailResponses.size();
    }


    private String saveToInternalStorage(Bitmap bitmapImage,String name){
        ContextWrapper cw = new ContextWrapper(context);
        Log.d("nameimage",name);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,name);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath()+"/"+name;
    }
}
