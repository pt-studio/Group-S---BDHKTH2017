package com.example.andrejlee.smartpotui.ui.bases;


import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ThienLee on 17-Apr-17.
 */

public abstract class BasePresenter<T extends MvpView> implements Presenter<T> {
    protected T mMvpView;
    protected CompositeDisposable mSubcriptions;

    public BasePresenter() {
    }

    @Override
    public void attachView(T mvpView) {
        mSubcriptions = new CompositeDisposable();
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mSubcriptions.dispose();
        mSubcriptions = null;
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) {
            throw new MvpViewNotAttachedException();
        }
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
