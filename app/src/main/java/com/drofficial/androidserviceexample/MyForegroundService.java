package com.drofficial.androidserviceexample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class MyForegroundService extends Service {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (true){
                            Log.e("Service", "Foreground Service is running...");
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ).start();

        final String CHANNEL_ID ="Foreground Channel Id";
        final String CHANNEL ="Foreground Channel";
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,CHANNEL, NotificationManager.IMPORTANCE_LOW);

        getSystemService(NotificationManager.class).createNotificationChannel(notificationChannel);

        Notification.Builder notificationbuilder =  new Notification.Builder(this,CHANNEL_ID);
        notificationbuilder.setContentTitle("Forground Service");
        notificationbuilder.setContentText("Forground Service is running......");
        notificationbuilder.setSmallIcon(R.drawable.ic_launcher_foreground);

        startForeground(1001,notificationbuilder.build());

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
