package com.example.andrejlee.smartpotui.application;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.example.andrejlee.smartpotui.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class MyApplication extends MultiDexApplication {

    private static MyApplication mInstance;

    //    PUBLIC METHODS - APP GETTERS
    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    //    OVERRIDE METHODS
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
//        initRealm();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Pro-m.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    //    PRIVATE METHODS
//    private void initRealm() {
//        Realm.init(this);
//        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
//                .name(Realm.DEFAULT_REALM_NAME)
//                .schemaVersion(1)
//                .deleteRealmIfMigrationNeeded()
//                .build();
//        Realm.setDefaultConfiguration(realmConfiguration);
//    }

    //    PUBLIC METHODS
//    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
//        ConnectivityReceiver.sConnectivityReceiverListener = listener;
//    }
}
