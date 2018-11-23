package com.example.kishanmadhwani.seriesguide.database;

import android.provider.BaseColumns;

public class FeedReaderContract {
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {

        public static final String TABLE_NAME = "movie";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_RUNTIME = "runtime";
        public static final String COLUMN_NAME_DESCRIPTION = "desc";
        public static final String COLUMN_NAME_GENRES = "genres";
        public static final String COLUMN_NAME_RATING = "rating";
        public static final String COLUMN_NAME_COLLECTION = "collection";
        public static final String COLUMN_NAME_WATCHLIST = "watchlist";
        public static final String COLUMN_NAME_IGNOREARTICLE = "ignorearticle";
        public static final String COLUMN_NAME_POSTERPATH = "posterpath";
        public static final String COLUMN_NAME_MOVIEID = "id";

    }



}
