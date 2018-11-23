package com.example.kishanmadhwani.seriesguide.response;

import com.google.gson.annotations.SerializedName;

public class GenreDetailResponse {
    @SerializedName("name")
    private String name;

    public GenreDetailResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
