package com.example.finalmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.finalmobile.models.MovieModel;
import com.example.finalmobile.request.Service;
import com.example.finalmobile.response.MovieSearchResponse;
import com.example.finalmobile.utils.Credentials;
import com.example.finalmobile.utils.MovieApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetRetrofitResponse();
            }
        });
    }

    private void GetRetrofitResponse() {
        MovieApi movieApi = Service.getMovieApi();
        Call<MovieSearchResponse> responseCall = movieApi
                .searchMovie(
                        Credentials.API_KEY,
                        "Jack Reacher",
                        1);

     responseCall.enqueue(new Callback<MovieSearchResponse>() {
         @Override
         public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
             if(response.code() == 200){
                 Log.v("Tag", "the response" + response.body().toString());
                 List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                 for(MovieModel movie: movies){
                     Log.v("Tag", "the release date" + movie.getRelease_date());
                 }
             }
             else{
                 try {
                     Log.v("Tag", "Error" + response.errorBody().string());
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
         }

         @Override
         public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

         }
     });
    }
}