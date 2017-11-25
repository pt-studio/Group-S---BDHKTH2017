package com.example.andrejlee.smartpotui.ui.activities.detail;

import com.example.andrejlee.smartpotui.common.SmartPotFacade;
import com.example.andrejlee.smartpotui.entities.api.TreeEntity;
import com.example.andrejlee.smartpotui.ui.bases.BasePresenter;


public class DetailTreePresenter extends BasePresenter<DetailTreeView> {

    private TreeEntity currentTree;

    public void getDeviceById(int id) {
        SmartPotFacade.getInstance().getDeviceById(id)
                .subscribe(returnValue -> {
                    currentTree = returnValue;
                    if (mMvpView != null) {
//                        mMvpView.setContentSafeMoisture(String.valueOf(returnValue.getAttribute().getSaveValue()) + "/100");
//                        mMvpView.setContentCurrentMoisture(String.valueOf(returnValue.getAttribute().getHumidity()) + "/100");
                        mMvpView.setScreenTitle(returnValue.getName());
                    }
                }, throwable -> {
                    if (mMvpView != null) {
                        mMvpView.showDialogMessageAPI(throwable.toString());
                    }
                });
    }

    public TreeEntity getCurrentTree() {
        return currentTree;
    }
}
