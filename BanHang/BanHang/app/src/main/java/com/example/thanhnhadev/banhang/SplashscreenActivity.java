package com.example.thanhnhadev.banhang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SplashscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    //thời gian chờ load màng hình
                    Thread.sleep(1000);
                }catch (Exception e){

                }finally {
                    //chờ xong load qua login
                    Intent trangchu = new Intent(SplashscreenActivity.this, LoginActivity.class);
                    startActivity(trangchu);
                    finish();
                }
            }
        });
        thread.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Splash","lan2");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Splash","end");
    }
}