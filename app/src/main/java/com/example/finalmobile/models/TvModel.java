package com.example.finalmobile.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TvModel implements Parcelable {
    // Model Class
    private String name;
    private String poster_path;
    private String first_air_date;
    @SerializedName("id")
    private int tv_id;
    private float vote_average;

    @SerializedName("overview")
    private String movie_overview;






    public TvModel(String poster_path, float vote_average) {
        this.poster_path = poster_path;
        this.vote_average = vote_average;
    }

    public TvModel(String name, String poster_path, String first_air_date_date, int tv_id, float vote_average, String movie_overview) {
        this.name = name;
        this.poster_path = poster_path;
        this.first_air_date = first_air_date_date;
        this.tv_id = tv_id;
        this.vote_average = vote_average;
        this.movie_overview = movie_overview;

    }

    protected TvModel(Parcel in) {
        name = in.readString();
        poster_path = in.readString();
        first_air_date = in.readString();
        tv_id = in.readInt();
        vote_average = in.readFloat();
        movie_overview = in.readString();
    }

    public static final Creator<TvModel> CREATOR = new Creator<TvModel>() {
        @Override
        public TvModel createFromParcel(Parcel in) {
            return new TvModel(in);
        }

        @Override
        public TvModel[] newArray(int size) {
            return new TvModel[size];
        }
    };

    // Getters
    public String getName() {
        return name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }
    public int getTv_id() {
        return tv_id;
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
        parcel.writeString(name);
        parcel.writeString(poster_path);
        parcel.writeString(first_air_date);
        parcel.writeInt(tv_id);
        parcel.writeFloat(vote_average);
        parcel.writeString(movie_overview);

    }



}
