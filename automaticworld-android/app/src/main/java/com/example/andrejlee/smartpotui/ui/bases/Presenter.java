package com.example.andrejlee.smartpotui.ui.bases;

public interface Presenter<V extends MvpView> {
    void attachView(V mvpView);

    void detachView();
}
