package com.example.dongja94.sampleapplicationcomponent;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    private static final String TAG = "MyService";

    public MyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    int mCount = 0;
    boolean isRunning = false;
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "onCreate..." , Toast.LENGTH_SHORT).show();

        isRunning = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    Log.i(TAG, "count : " + mCount);
                    mCount++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            Toast.makeText(this, "onStartCommand...", Toast.LENGTH_SHORT).show();
            int count = intent.getIntExtra("count", 0);
            mCount = count;
        } else {
            Log.i(TAG, "restart service...");
        }
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy..." , Toast.LENGTH_SHORT).show();
        isRunning = false;
    }
}
