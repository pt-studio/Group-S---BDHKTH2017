package com.example.andrejlee.smartpotui.ui.fragments.light;

import com.example.andrejlee.smartpotui.ui.bases.MvpView;

/**
 * Created by Andrej Lee on 11/25/2017.
 */

public interface LightTabView extends MvpView {

    void setContentStatus(String content);

    //    Light
    void setContentSafeLightUpper(String content);

    void setContentCurrentLight(String content);

    void setContentSafeLightLower(String content);
}
