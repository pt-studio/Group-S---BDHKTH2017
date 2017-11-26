package com.example.andrejlee.smartpotui.ui.fragments.add_tree;

import com.example.andrejlee.smartpotui.common.SmartPotFacade;
import com.example.andrejlee.smartpotui.ui.bases.BasePresenter;


public class AddTreeFragmentPresenter extends BasePresenter<AddTreeFragmentView> {

    public void addTree(String name, int deviceType, int safeValue) {
        SmartPotFacade.getInstance().addTree(name, deviceType, safeValue)
                .subscribe(returnValue -> {
                    if (mMvpView != null){
                        mMvpView.showDialogMessageAPI("Tạo cây thành công!");
                    }
                }, throwable -> {
                    if (mMvpView != null){
                        mMvpView.showDialogMessageAPI(throwable.toString());
                    }
                });
    }
}
