package com.example.andrejlee.smartpotui.ui.activities.growing_tips_detail;

import com.example.andrejlee.smartpotui.ui.bases.MvpView;

public interface GrowingTipsDetailView extends MvpView {
    void setImageContent(int image);

    void setTitleContent(String title);

    void setDescriptionContent(String content);
}
