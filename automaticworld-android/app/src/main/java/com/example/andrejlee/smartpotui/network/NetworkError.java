package com.example.andrejlee.smartpotui.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


public class NetworkError extends Throwable {
    private static final String TAG = NetworkError.class.getSimpleName();
    private static boolean sIsNetworkError;
    private final Throwable ERROR;

    //    CONSTRUCTOR
    public NetworkError(Throwable e) {
        super(e);
        ERROR = e;
    }

    //    PUBLIC METHODS
    public static boolean sIsNetworkError() {
        return sIsNetworkError;
    }

    public static void setIsNetworkError(boolean isNetworkError) {
        NetworkError.sIsNetworkError = isNetworkError;
    }

    public static boolean isInternetAvailable(Context context) {
        NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (info == null) {
            Log.d(TAG, "no internet connection");
            return false;
        } else {
            if (info.isConnected()) {
                Log.d(TAG, " internet connection available...");
                return true;
            } else {
                Log.d(TAG, " internet connection");
                return true;
            }

        }
    }

    public String getMessage() {
        return ERROR != null ? ERROR.getMessage() : "";
    }
}
