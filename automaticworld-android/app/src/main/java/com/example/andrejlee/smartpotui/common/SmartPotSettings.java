package com.example.andrejlee.smartpotui.common;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

/**
 * Created by Andrej Lee on 11/15/2017.
 */

public class SmartPotSettings {

    private static final String SHAREPREF_NAME = "prefs";
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
}
