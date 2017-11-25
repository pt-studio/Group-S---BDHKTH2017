package com.example.andrejlee.smartpotui.ui.bases;


import android.view.View;

import com.example.andrejlee.smartpotui.constants.Constants;


public interface MvpView {
    void showViewState(Constants.ViewState state);

    void showDialogMessageAPI(String content);
}
