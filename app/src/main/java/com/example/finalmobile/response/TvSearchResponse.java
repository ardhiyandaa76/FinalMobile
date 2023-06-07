package com.example.finalmobile.response;

import com.example.finalmobile.models.TvModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvSearchResponse {
    @SerializedName("total_results")
    @Expose()
    private int total_count;

    @SerializedName("results")
    @Expose()
    private List<TvModel> tvs;

    public int getTotal_count(){
        return total_count;
    }
    public List<TvModel> getTvs(){
        return tvs;
    }

    @Override
    public String toString() {
        return "TvSearchResponse{" +
                "total_count=" + total_count +
                ", tvs=" + tvs +
                '}';
    }
}
