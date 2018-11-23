package com.example.kishanmadhwani.seriesguide.response;

import com.google.gson.annotations.SerializedName;

public class MovieBasicDetailResponse {
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("original_title")
    private String title;
    @SerializedName("id")
    private String id;
    @SerializedName("release_date")
    private String releaseDate;

    public MovieBasicDetailResponse(String posterPath, String title, String id, String releaseDate) {
        this.posterPath = posterPath;
        this.title = title;
        this.id = id;
        this.releaseDate = releaseDate;
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
}
