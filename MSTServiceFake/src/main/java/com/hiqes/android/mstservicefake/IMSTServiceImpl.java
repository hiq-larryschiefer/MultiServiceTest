package com.hiqes.android.mstservicefake;

import android.os.RemoteException;

import com.hiqes.android.mstcommon.IMSTService;

public class IMSTServiceImpl extends IMSTService.Stub {
    private String              MY_ID = "The imposter!";

    @Override
    public String getID() throws RemoteException {
        return MY_ID;
    }
}
