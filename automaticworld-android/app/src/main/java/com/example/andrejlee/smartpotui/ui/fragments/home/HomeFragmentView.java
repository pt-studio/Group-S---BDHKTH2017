package com.example.andrejlee.smartpotui.ui.fragments.home;

import com.example.andrejlee.smartpotui.entities.api.TreeEntity;
import com.example.andrejlee.smartpotui.ui.bases.MvpView;

import java.util.List;

public interface HomeFragmentView extends MvpView {

    void loadDataToGridView(List<TreeEntity> data);
}
