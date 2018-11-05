package com.example.kishanmadhwani.seriesguide;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesWatchlistFragment extends Fragment {

    FeedReaderDbHelper mDbHelper ;
    SQLiteDatabase db;
    ArrayList<WatchList> watchlist=new ArrayList<>();
    public MoviesWatchlistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_movies_watchlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.theater_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        mDbHelper = new FeedReaderDbHelper(getActivity());
        if(mDbHelper!=null) {
            Log.d("inyes","yes");
            db = mDbHelper.getReadableDatabase();
            String[] projection = {
                    BaseColumns._ID,
                    FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
                    FeedReaderContract.FeedEntry.COLUMN_NAME_DATE,
                    FeedReaderContract.FeedEntry.COLUMN_NAME_POSTERPATH
            };

            Cursor c = db.query(
                    FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    null,
                    null,                   // don't group the rows
                    null,        // The sort order
                    null,
                    null
            );
            int i=0;
            int titlecolumn=c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE);
            int datecolumn=c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE);
            int posterpathcolumn=c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_POSTERPATH);
                while (c.moveToNext()) {
                    i++;
                    long itemId = c.getLong(
                            c.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID));
                        watchlist.add(new WatchList(c.getString(titlecolumn), c.getString((datecolumn)), c.getString(posterpathcolumn)));
                    Log.d("watchlist", c.getString(titlecolumn) + "  " + c.getString(datecolumn) + "  " + c.getString(posterpathcolumn));
                }
                c.close();
        }


        recyclerView.setAdapter(new WatchListAdapter(watchlist,R.layout.grid_item,getActivity()));
    }

    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.moviemainmenu, menu);
        menu.findItem(R.id.refresh).setVisible(false);
        menu.findItem(R.id.language).setVisible(false);
    }

}
