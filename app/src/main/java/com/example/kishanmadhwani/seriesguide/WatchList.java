package com.example.kishanmadhwani.seriesguide;

public class WatchList {
    String date,title,posterpath;
    int collection;

    public WatchList(String title, String date, String posterpath,int collection) {
        this.date = date;
        this.title = title;
        this.posterpath = posterpath;
        this.collection=collection;
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
}

