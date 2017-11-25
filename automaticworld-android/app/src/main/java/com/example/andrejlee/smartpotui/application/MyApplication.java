package com.example.andrejlee.smartpotui.application;

import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.util.Utils;
import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.common.SmartPotSettings;
import com.example.andrejlee.smartpotui.network.SmartPotNetworkManager;
import com.example.andrejlee.smartpotui.services.RegistrationService;

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
        Utils.init(this);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/FallingSkyBd.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        Intent i = new Intent(this, RegistrationService.class);
        startService(i);
        SmartPotSettings.init(this);
//        TODO INIT FAKE USER
        SmartPotSettings.initUser();
        SmartPotNetworkManager.init();
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
