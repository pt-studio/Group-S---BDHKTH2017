package com.example.andrejlee.smartpotui.ui.fragments.temperature;

import com.example.andrejlee.smartpotui.ui.bases.MvpView;

/**
 * Created by Andrej Lee on 11/25/2017.
 */

public interface TempTabView extends MvpView {

    void setContentStatus(String content);

    //    Temperature
    void setContentSafeTempUpper(String content);

    void setContentCurrentTemp(String content);

    void setContentSafeTempLower(String content);
}
