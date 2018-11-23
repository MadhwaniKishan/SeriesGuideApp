package com.example.kishanmadhwani.seriesguide.response;

import com.google.gson.annotations.SerializedName;

public class PersonDetailResponse {
    @SerializedName("profile_path")
    private String posterPath;
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private String id;
    @SerializedName("biography")
    private String bio;

    public PersonDetailResponse(String posterPath, String name, String id, String bio) {
        this.posterPath = posterPath;
        this.name = name;
        this.id = id;
        this.bio = bio;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
