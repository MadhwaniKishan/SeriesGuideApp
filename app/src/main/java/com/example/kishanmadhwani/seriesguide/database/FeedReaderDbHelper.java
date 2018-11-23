package com.example.kishanmadhwani.seriesguide.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FeedReaderDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_DATE + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_RUNTIME + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_DESCRIPTION + "TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_GENRES + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_RATING + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_COLLECTION+ " NUMERIC," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_WATCHLIST+ " NUMERIC," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_IGNOREARTICLE + " NUMERIC," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_POSTERPATH + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_MOVIEID + " NUMERIC)";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SeriesGuide.db";

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
