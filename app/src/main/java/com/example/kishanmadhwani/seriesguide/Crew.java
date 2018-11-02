package com.example.kishanmadhwani.seriesguide;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Crew implements Serializable {
    @SerializedName("name")
    private String name;
    @SerializedName("job")
    private String job;
    @SerializedName("profile_path")
    private String profile;
    @SerializedName("id")
    private String id;

    public Crew(String name, String job, String profile, String id) {
        this.name = name;
        this.job = job;
        this.profile = profile;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
