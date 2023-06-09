package com.example.finalmobile.utils;

import com.example.finalmobile.models.MovieModel;
import com.example.finalmobile.models.TvModel;
import com.example.finalmobile.response.MovieSearchResponse;
import com.example.finalmobile.response.TvSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("/3/search/movie")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") int page
    );

    @GET("/3/movie/popular")
    Call<MovieSearchResponse> getPopular(
            @Query("api_key") String key,
            @Query("page") int page

    );

    @GET("3/movie/{movie_id}?")
    Call<MovieModel> getMovie(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key
    );

    @GET("/3/search/tv")
    Call<TvSearchResponse> searchTv(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") int page
    );

    @GET("/3/tv/popular")
    Call<TvSearchResponse> getPopTv(
            @Query("api_key") String key,
            @Query("page") int page

    );

    @GET("3/tv/{tv_id}?")
    Call<TvModel> getTv(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key
    );
}
