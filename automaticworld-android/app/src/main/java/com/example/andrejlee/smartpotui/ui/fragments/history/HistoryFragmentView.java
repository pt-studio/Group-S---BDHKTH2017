package com.example.andrejlee.smartpotui.ui.fragments.history;

import com.example.andrejlee.smartpotui.entities.api.NotiEntity;
import com.example.andrejlee.smartpotui.ui.bases.MvpView;

import java.util.List;

public interface HistoryFragmentView extends MvpView {
    void updateData(List<NotiEntity> result);

    void showEmptyLayout(boolean isShow);
}
