package com.example.andrejlee.smartpotui.ui.bases;

/**
 * Created by ThienLee on 17-Apr-17.
 */

public interface Presenter<V extends MvpView> {
    void attachView(V mvpView);

    void detachView();
}
