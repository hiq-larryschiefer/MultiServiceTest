package com.hiqes.android.mstservicereal;

import android.os.RemoteException;

import com.hiqes.android.mstcommon.IMSTService;

public class IMSTServiceImpl extends IMSTService.Stub {
    private String              MY_ID = "The real deal!";

    @Override
    public String getID() throws RemoteException {
        return MY_ID;
    }
}
