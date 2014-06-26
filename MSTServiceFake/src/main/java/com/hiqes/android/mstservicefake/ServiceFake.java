package com.hiqes.android.mstservicefake;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ServiceFake extends Service {
    private static final String TAG = "ServiceFake";
    private IMSTServiceImpl     mService;

    @Override
    public void onCreate() {
        super.onCreate();
        mService = new IMSTServiceImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind for FAKE");
        return mService;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
