package com.example.finalmobile.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class TvModel implements Parcelable {
    //Model for our tv shows
    private String title;
    private String poster_path;
    private String first_air_date;
    private int tv_id;
    private float vote_average;

    @SerializedName("overview")
    private String tv_overview;
    private String original_language;

    public TvModel(String poster_path, float vote_average){
        this.poster_path = poster_path;
        this.vote_average = vote_average;
    }

    public TvModel(String title, String poster_path, String first_air_date, int tv_id, float vote_average, String tv_overview, String original_language) {
        this.title = title;
        this.poster_path = poster_path;
        this.first_air_date = first_air_date;
        this.tv_id = tv_id;
        this.vote_average = vote_average;
        this.tv_overview = tv_overview;
        this.original_language = original_language;
    }


    protected TvModel(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        first_air_date = in.readString();
        tv_id = in.readInt();
        vote_average = in.readFloat();
        tv_overview = in.readString();
        original_language = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(poster_path);
        parcel.writeString(first_air_date);
        parcel.writeInt(tv_id);
        parcel.writeFloat(vote_average);
        parcel.writeString(tv_overview);
        parcel.writeString(original_language);
    }

    public String getTitle() {
        return title;
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

    public String getTv_overview() {
        return tv_overview;
    }

    public String getOriginal_language() {
        return original_language;
    }

    @Override
    public String toString() {
        return "TvModel{" +
                "title='" + title + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", first_air_date='" + first_air_date + '\'' +
                ", tv_id=" + tv_id +
                ", vote_average=" + vote_average +
                ", tv_overview='" + tv_overview + '\'' +
                ", original_language='" + original_language + '\'' +
                '}';
    }
}
