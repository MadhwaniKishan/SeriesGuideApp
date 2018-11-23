package com.example.kishanmadhwani.seriesguide.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreditsResponse {
    @SerializedName("id")
    private String id;
    @SerializedName("crew")
    private List<CrewDetailsResponse> crewDetailResponses;
    @SerializedName("cast")
    private List<CastDetailResponse> castDetailResponse;

    public CreditsResponse(String id, List<CrewDetailsResponse> crewDetailResponses, List<CastDetailResponse> castDetailResponse) {
        this.id = id;
        this.crewDetailResponses = crewDetailResponses;
        this.castDetailResponse = castDetailResponse;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CrewDetailsResponse> getCrewDetailResponses() {
        return crewDetailResponses;
    }

    public void setCrewDetailResponses(List<CrewDetailsResponse> crewDetailResponses) {
        this.crewDetailResponses = crewDetailResponses;
    }

    public List<CastDetailResponse> getCastDetailResponse() {
        return castDetailResponse;
    }

    public void setCastDetailResponse(List<CastDetailResponse> castDetailResponse) {
        this.castDetailResponse = castDetailResponse;
    }
}
