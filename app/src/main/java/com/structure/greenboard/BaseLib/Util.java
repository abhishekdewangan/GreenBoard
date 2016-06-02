package com.structure.greenboard.BaseLib;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.structure.greenboard.MyApplication;


/**
 * Created by abhishekdewa on 5/13/2016.
 */
public class Util {
    public static boolean checkNetworkConnection() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication.getAppContext().getSystemService(MyApplication.getAppContext().CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        } else {
            connected = false;
            Toast.makeText(MyApplication.getAppContext(), "Connection Problem", Toast.LENGTH_LONG).show();
        }
        return connected;
    }
}
