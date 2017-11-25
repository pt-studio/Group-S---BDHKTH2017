package com.example.andrejlee.smartpotui.ui.fragments.moisture;

import com.example.andrejlee.smartpotui.ui.bases.MvpView;

/**
 * Created by Andrej Lee on 11/25/2017.
 */

public interface MoistureTabView extends MvpView {

    void setContentStatus(String content);

    //    Moisture
    void setContentSafeMoistureUpper(String content);

    void setContentCurrentMoisture(String content);

    void setContentSafeMoistureLower(String content);
}
