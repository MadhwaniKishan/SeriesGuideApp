package com.example.kishanmadhwani.seriesguide;

public class WatchList {
    String date,title,posterpath;

    public WatchList(String title, String date, String posterpath) {
        this.date = date;
        this.title = title;
        this.posterpath = posterpath;
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
}

