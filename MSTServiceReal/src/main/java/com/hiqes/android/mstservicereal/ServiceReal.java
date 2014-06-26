package com.hiqes.android.mstservicereal;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ServiceReal extends Service {
    private static final String TAG = "ServiceReal";
    private IMSTServiceImpl     mService;

    @Override
    public void onCreate() {
        super.onCreate();
        mService = new IMSTServiceImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return mService;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
