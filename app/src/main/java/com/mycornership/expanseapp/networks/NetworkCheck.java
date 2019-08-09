package com.mycornership.expanseapp.networks;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkCheck {
    public static boolean isConnected(Context context){
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean connected = false;

        if(manager != null){
            NetworkInfo info = manager.getActiveNetworkInfo();
            connected = (info != null) && (info.isConnected());
        }
        return connected;
    }
}
