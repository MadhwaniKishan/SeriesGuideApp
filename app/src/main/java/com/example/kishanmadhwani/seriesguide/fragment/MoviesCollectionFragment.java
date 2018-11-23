package com.example.kishanmadhwani.seriesguide.fragment;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kishanmadhwani.seriesguide.R;
import com.example.kishanmadhwani.seriesguide.others.WatchList;
import com.example.kishanmadhwani.seriesguide.adapter.WatchListAdapter;
import com.example.kishanmadhwani.seriesguide.database.FeedReaderContract;
import com.example.kishanmadhwani.seriesguide.database.FeedReaderDbHelper;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesCollectionFragment extends Fragment {

    FeedReaderDbHelper mDbHelper ;
    SQLiteDatabase db;
    ArrayList<WatchList> collection=new ArrayList<>();
    RecyclerView recyclerView;

    public MoviesCollectionFragment() {
        // Required empty public constructor
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            collection = new ArrayList<>();
            recyclerView = (RecyclerView) getView().findViewById(R.id.theater_recycler_view);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
            mDbHelper = new FeedReaderDbHelper(getActivity());
            if (mDbHelper != null) {
                Log.d("inyes", "yes");
                db = mDbHelper.getReadableDatabase();
                String[] projection = {
                        BaseColumns._ID,
                        FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
                        FeedReaderContract.FeedEntry.COLUMN_NAME_DATE,
                        FeedReaderContract.FeedEntry.COLUMN_NAME_POSTERPATH,
                        FeedReaderContract.FeedEntry.COLUMN_NAME_COLLECTION,
                        FeedReaderContract.FeedEntry.COLUMN_NAME_MOVIEID
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
                int i = 0;
                int titlecolumn = c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE);
                int datecolumn = c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE);
                int posterpathcolumn = c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_POSTERPATH);
                int collectioncolumn = c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_COLLECTION);
                int id = c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MOVIEID);//get id
                while (c.moveToNext()) {
                    i++;
                    long itemId = c.getLong(
                            c.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID));
                    if (c.getInt(collectioncolumn) == 2)
                        collection.add(new WatchList(c.getString(titlecolumn), c.getString((datecolumn)), c.getString(posterpathcolumn), c.getInt(collectioncolumn), c.getInt(id)));
                    Log.d("watchlist", c.getString(titlecolumn) + "  " + c.getString(datecolumn) + "  " + c.getString(posterpathcolumn) + "  " + c.getString(collectioncolumn));
                }
                c.close();
            }


            recyclerView.setAdapter(new WatchListAdapter(collection, R.layout.grid_item, getActivity()));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_movies_collection, container, false);
    }
    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.moviemainmenu, menu);
        menu.findItem(R.id.refresh).setVisible(false);
        menu.findItem(R.id.language).setVisible(false);
    }

}
