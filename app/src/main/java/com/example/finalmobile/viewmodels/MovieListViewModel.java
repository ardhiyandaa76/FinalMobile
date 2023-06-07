package com.example.finalmobile.viewmodels;

import androidx.lifecycle.LiveData;

import com.example.finalmobile.models.MovieModel;
import com.example.finalmobile.repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel {
    private MovieRepository movieRepository;

    public MovieListViewModel(){
        movieRepository = MovieRepository.getInstance();
    }
    public LiveData<List<MovieModel>> getMovies(){
        return movieRepository.getMovies();
    }
    public void searchMovieApi(String query, int pageNumber){
        movieRepository.searchMovieApi(query, pageNumber);
    }
}
