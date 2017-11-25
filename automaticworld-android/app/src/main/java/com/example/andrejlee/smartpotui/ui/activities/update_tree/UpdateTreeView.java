package com.example.andrejlee.smartpotui.ui.activities.update_tree;

import com.example.andrejlee.smartpotui.ui.bases.MvpView;


public interface UpdateTreeView extends MvpView {

    void setContentTreeName(String name);

    void setSeekBarProgress(int value);

    void setContentProgressValue(String value);

    void setContentCurrentUser(String value);
}
