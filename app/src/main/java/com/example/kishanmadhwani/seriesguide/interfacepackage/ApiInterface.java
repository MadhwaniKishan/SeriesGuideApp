package com.example.kishanmadhwani.seriesguide.interfacepackage;

import com.example.kishanmadhwani.seriesguide.response.CreditsResponse;
import com.example.kishanmadhwani.seriesguide.response.MovieDetailResponse;
import com.example.kishanmadhwani.seriesguide.response.MovieResponse;
import com.example.kishanmadhwani.seriesguide.response.PersonDetailResponse;
import com.example.kishanmadhwani.seriesguide.response.SeriesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("discover/tv")
    Call<SeriesResponse> discoverSeries(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") String page, @Query("include_null_first_air_dates") String nullFirst);
//movie/top_rated?api_key=c7aee5e9037e1339e8702a3fff97677f

    @GET("movie/{id}")
    Call<MovieDetailResponse> movieDetail(@Path("id") int id, @Query("api_key") String apiKey, @Query("language") String language, @Query("page") String page, @Query("include_null_first_air_dates") String nullFirst);

    @GET("movie/{id}/credits")
    Call<CreditsResponse> creditDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/now_playing")
    Call<MovieResponse> discoverMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") String page, @Query("include_null_first_air_dates") String nullFirst);

    @GET("tv/popular")
    Call<SeriesResponse> popularSeries(@Query("api_key") String apiKey,@Query("language") String language,@Query("page") String page,@Query("include_null_first_air_dates") String nullFirst);

    @GET("movie/popular")
    Call<MovieResponse> popularMovies(@Query("api_key") String apiKey,@Query("language") String language,@Query("page") String page,@Query("include_null_first_air_dates") String nullFirst);

    @GET("movie/{id}")
    Call<SeriesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("person/{id}")
    Call<PersonDetailResponse> getPersonDetail(@Path("id") int id, @Query("api_key") String apiKey, @Query("language") String language);

}
