package com.example.finalmobile.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MovieModel implements Parcelable {
    // Model Class
    private String title;
    private String poster_path;
    private String release_date;
    private String backdrop_path;
    @SerializedName("id")
    private int movie_id; // id is int
    private float vote_average;

    @SerializedName("overview")
    private String movie_overview;




    public MovieModel(String poster_path, float vote_average) {
        this.poster_path = poster_path;
        this.vote_average = vote_average;
    }

    public MovieModel(String title, String poster_path, String release_date, int movie_id, float vote_average, String movie_overview) {
        this.title = title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.movie_id = movie_id;
        this.vote_average = vote_average;
        this.movie_overview = movie_overview;

    }

    protected MovieModel(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        movie_id = in.readInt();
        vote_average = in.readFloat();
        movie_overview = in.readString();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    // Getters
    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getMovie_overview() {
        return movie_overview;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(poster_path);
        parcel.writeString(release_date);
        parcel.writeInt(movie_id);
        parcel.writeFloat(vote_average);
        parcel.writeString(movie_overview);
    }
}

