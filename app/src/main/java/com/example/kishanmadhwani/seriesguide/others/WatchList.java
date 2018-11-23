package com.example.kishanmadhwani.seriesguide.others;

public class WatchList {
    String date,title,posterpath;
    int collection,watchlist;
    int id;
    public WatchList(String title, String date, String posterpath,int collection, int id) {
        this.date = date;
        this.title = title;
        this.posterpath = posterpath;
        this.collection=collection;
        this.id=id;
    }

    public String getPosterpath() {
        return posterpath;
    }

    public void setPosterpath(String posterpath) {
        this.posterpath = posterpath;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

