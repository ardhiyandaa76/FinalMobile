package com.example.finalmobile.repositories;

import androidx.lifecycle.LiveData;

import com.example.finalmobile.models.TvModel;
import com.example.finalmobile.request.TvApiClient;

import java.util.List;

public class TvRepository {
    private static TvRepository instance;
    private TvApiClient tvApiClient;
    private String mQuery;
    private int mPageNumber;

    public static TvRepository getInstance() {
        if (instance == null) {
            instance = new TvRepository();
        }
        return instance;

    }
    private TvRepository(){
        tvApiClient = TvApiClient.getInstance();
    }
    public LiveData<List<TvModel>> getTvs(){
        return tvApiClient.getTvs();
    }

    public void searchTvApi(String query, int pageNumber){
        mQuery = query;
        mPageNumber = pageNumber;
        tvApiClient.searchTvsApi(query, pageNumber);
    }

    public void searchNextPage(){
        searchTvApi(mQuery, mPageNumber+1);
    }

    public LiveData<List<TvModel>> getPop(){
        return tvApiClient.getTvsPop();
    }


    public void searchTvPop(int pageNumber){

        mPageNumber = pageNumber;
        tvApiClient.searchTvsPop( pageNumber);
    }

}
