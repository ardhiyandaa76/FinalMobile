package com.example.finalmobile.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalmobile.BackgroundThread;
import com.example.finalmobile.models.TvModel;
import com.example.finalmobile.response.TvSearchResponse;
import com.example.finalmobile.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class TvApiClient {
    private MutableLiveData<List<TvModel>> mTvs;
    private MutableLiveData<List<TvModel>> mTvsPop;
    private static TvApiClient instance;
    private RetrieveTvsRunnable retrieveTvsRunnable;
    private RetrieveTvsRunnablePop retrieveTvsRunnablePop;

    public static TvApiClient getInstance(){
        if (instance == null){
            instance = new TvApiClient();
        }
        return  instance;

    }

    private TvApiClient(){
        mTvs = new MutableLiveData<>();
        mTvsPop = new MutableLiveData<>();
    }


    public LiveData<List<TvModel>> getTvs(){
        return mTvs;
    }

    public LiveData<List<TvModel>> getTvsPop(){
        return mTvsPop;
    }




    public void searchTvsApi(String query, int pageNumber) {

        if (retrieveTvsRunnable != null){
            retrieveTvsRunnable = null;
        }

        retrieveTvsRunnable = new RetrieveTvsRunnable(query, pageNumber);

        final Future myHandler = BackgroundThread.getInstance().networkIO().submit(retrieveTvsRunnable);

        BackgroundThread.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // Cancelling the retrofit call
                myHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);


    }



    public void searchTvsPop(int pageNumber) {

        if (retrieveTvsRunnablePop != null){
            retrieveTvsRunnablePop = null;
        }

        retrieveTvsRunnablePop = new RetrieveTvsRunnablePop(pageNumber);

        final Future myHandler2 = BackgroundThread.getInstance().networkIO().submit(retrieveTvsRunnablePop);
        BackgroundThread.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // Cancelling the retrofit call
                myHandler2.cancel(true);

            }
        }, 1000, TimeUnit.MILLISECONDS);

    }

    private class RetrieveTvsRunnable implements Runnable{
        private String query;
        private int pageNumber;
        boolean cancelRequest;
        public RetrieveTvsRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try{

                Response response = getMovies(query, pageNumber).execute();

                if (cancelRequest) {

                    return;
                }
                if (response.code() == 200){
                    List<TvModel> list = new ArrayList<>(((TvSearchResponse)response.body()).getTvs());
                    if (pageNumber == 1){
                        mTvs.postValue(list);

                    }else{
                        List<TvModel> currentTvs = mTvs.getValue();
                        currentTvs.addAll(list);
                        mTvs.postValue(currentTvs);
                    }

                }else{
                    String error = response.errorBody().string();
                    Log.v("Tagy", "Error " +error);
                    mTvs.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                mTvs.postValue(null);

            }
        }

        // Search Method/ query
        private Call<TvSearchResponse> getMovies(String query, int pageNumber){
            return Service.getMovieApi().searchTv(
                    Credentials.API_KEY,
                    query,
                    pageNumber
            );
        }
        private void cancelRequest(){
            Log.v("Tag", "Cancelling Search Request" );
            cancelRequest = true;
        }
    }

    private class RetrieveTvsRunnablePop implements Runnable{
        private int pageNumber;
        boolean cancelRequest;
        public RetrieveTvsRunnablePop(int pageNumber) {

            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try{
                Response response2 = getPop(pageNumber).execute();
                if (cancelRequest) {
                    return;
                }
                if (response2.code() == 200){
                    List<TvModel> list = new ArrayList<>(((TvSearchResponse)response2.body()).getTvs());
                    if (pageNumber == 1){
                        mTvsPop.postValue(list);

                    }else{
                        List<TvModel> currentTvs = mTvsPop.getValue();
                        currentTvs.addAll(list);
                        mTvsPop.postValue(currentTvs);
                    }

                }else{
                    String error = response2.errorBody().string();
                    Log.v("Tagy", "Erroryy " +error);
                    mTvsPop.postValue(null);

                }

            } catch (IOException e) {
                e.printStackTrace();
                mTvsPop.postValue(null);
            }
        }

        // Search Method/ query
        private Call<TvSearchResponse> getPop(int pageNumber){
            return Service.getMovieApi().getPopTv(
                    Credentials.API_KEY,
                    pageNumber
            );

        }
        private void cancelRequest(){
            Log.v("Tag", "Cancelling Search Request" );
            cancelRequest = true;
        }
    }
}
