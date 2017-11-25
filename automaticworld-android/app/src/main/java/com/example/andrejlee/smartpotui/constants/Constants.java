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

//    @IntDef({TabId.MYPAGE, TabId.CARDCASE, TabId.SENDNUMBER, TabId.SETTING, TabId.TUTORIAL_CARDCASE})
//    @Retention(RetentionPolicy.SOURCE)
//    public @interface TabId {
//        int MYPAGE = 1;
//        int CARDCASE = 2;
//        int TUTORIAL_CARDCASE = 3;
//        int SENDNUMBER = 4;
//        int SETTING = 5;
//    }

    public enum ViewState {
        LOADING, FINISHED, NO_DATA, NETWORK_ERROR
    }
}
