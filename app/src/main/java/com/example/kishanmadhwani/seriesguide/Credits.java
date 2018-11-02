package com.example.kishanmadhwani.seriesguide;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Credits {
    @SerializedName("id")
    private String id;
    @SerializedName("crew")
    private List<Crew> crew;
    @SerializedName("cast")
    private List<Cast> cast;

    public Credits(String id, List<Crew> crew, List<Cast> cast) {
        this.id = id;
        this.crew = crew;
        this.cast = cast;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }
}
