package com.example.finalmobile;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class BackgroundThread {
    private static BackgroundThread instance;
    public static BackgroundThread getInstance(){
        if(instance == null){
            instance = new BackgroundThread();
        }
        return instance;
    }

    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);
    public ScheduledExecutorService networkIO(){
        return mNetworkIO;
    }
}
