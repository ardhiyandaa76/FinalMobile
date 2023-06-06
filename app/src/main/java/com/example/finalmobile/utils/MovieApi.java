package com.example.finalmobile.utils;

import com.example.finalmobile.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    //Search For Movies
    //https://api.themoviedb.org/3/search/movie?query=Jack+Reacher&api_key=72f23821b529b1058cffcd50b9cdff27
    @GET
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") String page
    );

}
