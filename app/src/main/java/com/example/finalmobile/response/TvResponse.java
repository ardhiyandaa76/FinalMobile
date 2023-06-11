package com.example.finalmobile.response;

import com.example.finalmobile.models.TvModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//Class For Single Movie Request
public class TvResponse {
    @SerializedName("results")
    @Expose
    private TvModel tv;


    public TvModel getMovie() {
        return tv;
    }

    @Override
    public String toString() {
        return "TvResponse{" +
                "tv=" + tv +
                '}';
    }
}
