package com.example.andrejlee.smartpotui.constants;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by ThienLee on 17-Apr-17.
 */

public class Constants {
    public static final int CONNECTION_TIME_OUT             = 60; //60 seconds
    public static final int READ_TIME_OUT                   = 60; //60 seconds
    public static final int NEGA_ONE_VALUE = -1;
    public static final String STATUS_WORKING = "working";
    public static final String STATUS_OFF = "off";
    public static final int IMAGE_STATUS_SMILE = 1;
    public static final int IMAGE_STATUS_CRY = 2;
    public static final int IMAGE_STATUS_ANGRY = 3;
    public static final String STRING_TREE_ID_INTENT = "tree_id_intent";
    public static final String EMPTY_STRING = "";
    public static final String STRING_BLOG_ID_INTENT = "blog_id_intent";


    public enum ViewState {
        LOADING, FINISHED, NO_DATA, NETWORK_ERROR
    }

    @IntDef({DevelopMode.DEVELOP, DevelopMode.PRODUCTION})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DevelopMode {
        int DEVELOP = 1;
        int PRODUCTION = 2;
    }

    @IntDef({TabId.HOME, TabId.HISTORY, TabId.TIPS, TabId.STORAGE, TabId.ADD, TabId.UPDATE, TabId.CHANGEPASS, TabId.ABOUT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TabId {
        int HOME = 1;
        int HISTORY = 2;
        int TIPS = 3;
        int STORAGE = 4;
        int ADD = 5;
        int UPDATE = 6;
        int CHANGEPASS = 7;
        int ABOUT = 8;
    }
}
