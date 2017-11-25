package com.example.andrejlee.smartpotui.common;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.text.Editable;

import com.example.andrejlee.smartpotui.entities.api.UserEntity;

public class SmartPotSettings {

    private static final String SHAREPREF_NAME = "prefs";
//    private static String CURRENT_USERNAME = "Lê Minh Sơn";
    private static UserEntity FAKE_USER;
    private static SharedPreferences sPrefs;

    // CONSTRUCTOR
    private SmartPotSettings() {
    }

    // PRIVATE METHODS
    private static SharedPreferences prefs(Context context) {
        if (sPrefs == null) {
            synchronized (SmartPotSettings.class) {
                if (sPrefs == null) {
                    sPrefs = context.getSharedPreferences(SHAREPREF_NAME, Context.MODE_PRIVATE);
                }
            }
        }
        return sPrefs;
    }

    // PUBLIC METHODS
    public static void init(Application app) {
        prefs(app);
    }

//    public static String getCurrentUsername() {
//        return CURRENT_USERNAME;
//    }

//    public static void setCurrentUsername(String currentUsername) {
//        CURRENT_USERNAME = currentUsername;
//    }

    public static void initUser() {
        FAKE_USER = new UserEntity();
        FAKE_USER.setUsername("leminhson");
        FAKE_USER.setPassword("1");
    }

    public static UserEntity getUser() {
        return FAKE_USER;
    }

    public static boolean checkValidUser(Editable userName, Editable password) {
        return (userName != null && userName.toString().equals(FAKE_USER.getUsername()) &&
                password != null && password.toString().equals(FAKE_USER.getPassword()));
    }
}
