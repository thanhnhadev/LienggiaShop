package com.example.thanhnhadev.banhang;

import android.app.Application;
import android.content.Context;

public class BanHangApplication extends Application{
    private static Context instance = null;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = getApplicationContext();
    }

    public static Context getInstance(){
        return instance;
    }
}
