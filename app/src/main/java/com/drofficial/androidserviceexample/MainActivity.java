package com.drofficial.androidserviceexample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent backgroundintent = new Intent(getApplicationContext(),MyBackgroundService.class);
        startService(backgroundintent);

        if (!foregroundServiceIsRunning()){
            Intent foregroundintent = new Intent(getApplicationContext(),MyForegroundService.class);
            startForegroundService(foregroundintent);
        }

    }

    public boolean foregroundServiceIsRunning(){
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo serviceInfo : activityManager.getRunningServices(Integer.MAX_VALUE)){
            if (MyForegroundService.class.getName().equals(serviceInfo.service.getClassName())){
                return true;
            }
        }

        return false;
    }
}