package com.example.finalmobile;

import java.util.concurrent.ScheduledExecutorService;

public class BackgroundThreat {
    private static BackgroundThreat instance;
    public static BackgroundThreat getInstance(){
        if(instance == null){
            instance = new BackgroundThreat();
        }
        return instance;
    }

    private final ScheduledExecutorService
}
