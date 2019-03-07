package com.example.ashutoshdwivedi.tasktreeapp;

import android.app.Application;
import android.os.Bundle;

public class ExpensiaApp extends Application {

private static ExpensiaApp mInsatance;


public static synchronized ExpensiaApp getInstance(){
    return mInsatance;
}

    @Override
    public void onCreate() {
        super.onCreate();
        mInsatance=this;
    }


    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}

