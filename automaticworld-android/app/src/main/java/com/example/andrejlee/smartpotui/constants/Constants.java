package com.example.andrejlee.smartpotui.constants;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Constants {
    public static final int CONNECTION_TIME_OUT             = 60; //60 seconds
    public static final int READ_TIME_OUT                   = 60; //60 seconds
    public static final int NEGA_ONE_VALUE                  = -1;
    public static final int ZERO_VALUE                      = 0;
    public static final String STATUS_WORKING               = "working";
    public static final int DEVICE_TYPE_POT                 = 1;
    public static final int DEVICE_TYPE_AUTO                = 2;
    public static final String STATUS_OFF                   = "off";
    public static final int IMAGE_STATUS_SMILE              = 1;
    public static final int IMAGE_STATUS_CRY                = 2;
    public static final int IMAGE_STATUS_ANGRY              = 3;
    public static final int COLLUMN_NUM                     = 3;
    public static final String STRING_TREE_ID_INTENT        = "tree_id_intent";
    public static final String EMPTY_STRING                 = "";
    public static final String STRING_BLOG_ID_INTENT        = "blog_id_intent";
    public static final String SEARCH_HINT_TEXT             = ":ic_search_gray:";
    public static final int SEARCH_TEXT_SIZE                = 16;
    public static final int REFRESH_DURATION                = 2;


    public enum ViewState {
        LOADING, FINISHED, NO_DATA, NETWORK_ERROR
    }

    @IntDef({DevelopMode.DEVELOP, DevelopMode.PRODUCTION})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DevelopMode {
        int DEVELOP = 1;
        int PRODUCTION = 2;
    }

    @IntDef({SearchTextPadding.LEFT, SearchTextPadding.TOP, SearchTextPadding.RIGHT, SearchTextPadding.BOTTOM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SearchTextPadding{
        int LEFT = 0;
        int TOP = 5;
        int RIGHT = 0;
        int BOTTOM = 0;
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
