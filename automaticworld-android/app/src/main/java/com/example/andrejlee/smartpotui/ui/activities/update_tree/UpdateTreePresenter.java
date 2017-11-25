package com.example.andrejlee.smartpotui.ui.activities.update_tree;

import com.example.andrejlee.smartpotui.common.SmartPotFacade;
import com.example.andrejlee.smartpotui.common.SmartPotSettings;
import com.example.andrejlee.smartpotui.entities.api.TreeEntity;
import com.example.andrejlee.smartpotui.ui.bases.BasePresenter;


public class UpdateTreePresenter extends BasePresenter<UpdateTreeView> {

    private TreeEntity currentTree;

    public void getDeviceById(int id) {
        if (mMvpView != null) {
            SmartPotFacade.getInstance().getDeviceById(id)
                    .subscribe(returnValue -> {
                        currentTree = returnValue;
                        mMvpView.setContentProgressValue(String.valueOf(returnValue.getAttribute().getSaveValue()));
                        mMvpView.setSeekBarProgress(returnValue.getAttribute().getSaveValue());
                        mMvpView.setContentTreeName(returnValue.getName());
                        mMvpView.setContentCurrentUser(SmartPotSettings.getUser().getUsername());
                    }, throwable -> {
                        mMvpView.showDialogMessageAPI(throwable.toString());
                    });
        }
    }

    public void updateTree(int id, String name, String username, int safeValue) {
        SmartPotFacade.getInstance().updateTree(id, name, username, safeValue)
                .subscribe(returnValue -> {
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
