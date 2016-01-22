package com.example.dongja94.sampleapplicationcomponent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MySMSReceiver extends BroadcastReceiver {
    public MySMSReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, MyService.class);
        i.putExtra("count", 1000);
        context.startService(i);
    }
}
