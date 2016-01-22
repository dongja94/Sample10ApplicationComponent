package com.example.dongja94.sampleapplicationcomponent;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    private static final String TAG = "MyService";

    public MyService() {

    }

    class MyServiceBinder extends IMyService.Stub {
        @Override
        public int getCount() throws RemoteException {
            return mCount;
        }
    }

    MyServiceBinder mBinder;

    @Override
    public IBinder onBind(Intent intent) {
        if (mBinder == null) {
            mBinder = new MyServiceBinder();
        }
        return mBinder;
    }

    class MyService2Binder extends IMyService2.Stub {
        @Override
        public int getId(String name) throws RemoteException {
            return 0;
        }
    }

    MyService2Binder mBinder2;

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
//                    Log.i(TAG, "count : " + mCount);
                    mCount++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);

        registerReceiver(mScreenReceiver, filter);

    }

    BroadcastReceiver mScreenReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                Log.i(TAG, "Screen ON .....");
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                Log.i(TAG, "Screen OFF ....");
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            Toast.makeText(this, "onStartCommand...", Toast.LENGTH_SHORT).show();
            int count = intent.getIntExtra("count", 0);
            mCount = count;
        } else {
            Log.i(TAG, "restart service...");
        }
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy..." , Toast.LENGTH_SHORT).show();
        isRunning = false;
        unregisterReceiver(mScreenReceiver);
    }
}
