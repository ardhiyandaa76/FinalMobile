package com.example.finalmobile.utils;

import com.example.finalmobile.models.MovieModel;
import com.example.finalmobile.models.TvModel;
import com.example.finalmobile.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    //Search For Movies
    //https://api.themoviedb.org/3/search/movie?query=Jack+Reacher&api_key=72f23821b529b1058cffcd50b9cdff27
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
    @GET("3/tv/airing_today")
    Call<TvModel> getTv(
            @Path("tv_id") int tv_id,
            @Query("api_key") String api_key
    );
    @GET("3/search/tv")
    Call<MovieSearchResponse> searchTv(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") String page
    );
}
