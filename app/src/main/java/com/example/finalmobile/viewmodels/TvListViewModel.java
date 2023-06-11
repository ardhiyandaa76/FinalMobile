package com.example.finalmobile.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalmobile.models.TvModel;
import com.example.finalmobile.repositories.TvRepository;

import java.util.List;

public class TvListViewModel extends ViewModel {
    private TvRepository tvRepository;
    public TvListViewModel() {
        tvRepository = TvRepository.getInstance();
    }

    public LiveData<List<TvModel>> getTvs(){
        return tvRepository.getTvs();
    }
    public LiveData<List<TvModel>> getPop(){
        return tvRepository.getPop();
    }

    public void searchTvApi(String query, int pageNumber){
        tvRepository.searchTvApi(query, pageNumber);
    }

    public void searchTvPop(int pageNumber){
        tvRepository.searchTvPop( pageNumber);
    }



    public void searchNextpage(){
        tvRepository.searchNextPage();
    }

}
