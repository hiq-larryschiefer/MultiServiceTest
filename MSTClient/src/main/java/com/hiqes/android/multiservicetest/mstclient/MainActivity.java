package com.hiqes.android.multiservicetest.mstclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.hiqes.android.mstcommon.IMSTService;


public class MainActivity extends Activity implements ServiceConnection, Handler.Callback {
    private static final String         TAG = "MSTClient";
    private static final int            CONNECTED = 1;
    private static final int            DISCONNECTED = 2;

    private IMSTService         mService;
    private Handler             mHandler = new Handler(this);
    private TextView            mServiceIDText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mServiceIDText = (TextView)findViewById(R.id.service_id_text);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!bindService(new Intent(IMSTService.class.getName()), this, BIND_AUTO_CREATE)) {
            Log.d(TAG, "Unable to bind to service!");
            Toast.makeText(this, "Unable to bind to service!", Toast.LENGTH_LONG);
            finish();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(new Intent(IMSTService.class.getName()));
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mService = IMSTService.Stub.asInterface(service);
        mHandler.sendEmptyMessage(CONNECTED);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        mService = null;
        mHandler.sendEmptyMessage(DISCONNECTED);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case CONNECTED:
                try {
                    mServiceIDText.setText(mService.getID());
                } catch (RemoteException e) {
                    Log.e(TAG, "Failed to get service ID from service!");
                    finish();
                }
                break;

            case DISCONNECTED:
                mServiceIDText.setText("");
                break;
        }

        return true;
    }
}
