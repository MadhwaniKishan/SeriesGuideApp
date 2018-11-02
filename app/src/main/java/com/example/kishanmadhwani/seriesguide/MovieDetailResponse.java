package com.example.kishanmadhwani.seriesguide;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailResponse {
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("original_title")
    private String title;
    @SerializedName("id")
    private String id;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("runtime")
    private String runtime;
    @SerializedName("overview")
    private String overview;
    @SerializedName("vote_average")
    private String votetmdb;
    @SerializedName("vote_count")
    private String votecounttmdb;
    @SerializedName("genres")
    private List<Genre> genre;

    public MovieDetailResponse(String posterPath, String title, String id, String releaseDate, String runtime, String overview, String votetmdb, String votecounttmdb, List<Genre> genre) {
        this.posterPath = posterPath;
        this.title = title;
        this.id = id;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.overview = overview;
        this.votetmdb = votetmdb;
        this.votecounttmdb = votecounttmdb;
        this.genre = genre;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getVotetmdb() {
        return votetmdb;
    }

    public void setVotetmdb(String votetmdb) {
        this.votetmdb = votetmdb;
    }

    public String getVotecounttmdb() {
        return votecounttmdb;
    }

    public void setVotecounttmdb(String votecounttmdb) {
        this.votecounttmdb = votecounttmdb;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }
}
