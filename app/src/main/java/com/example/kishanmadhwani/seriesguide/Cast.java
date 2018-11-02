package com.example.kishanmadhwani.seriesguide;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cast implements Serializable {
    @SerializedName("name")
    private String name;
    @SerializedName("character")
    private String character;
    @SerializedName("profile_path")
    private String profile;
    @SerializedName("id")
    private String id;

    public Cast(String name, String character, String profile, String id) {
        this.name = name;
        this.character = character;
        this.profile = profile;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
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
