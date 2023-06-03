package com.example.finalmobile.response;

import com.example.finalmobile.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//Class For Single Movie Request
public class MovieResponse {
    @SerializedName("results")
    @Expose
    private MovieModel movie;
    public MovieModel getMovieModel(){
        return movie;
    }
    @Override
    public String toString(){
    return "MovieResponse{"
            + "movie=" + movie + '}';
    }
}
