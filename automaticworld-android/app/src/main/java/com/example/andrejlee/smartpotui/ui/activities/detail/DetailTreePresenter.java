package com.example.andrejlee.smartpotui.ui.activities.detail;

import com.example.andrejlee.smartpotui.common.SmartPotFacade;
import com.example.andrejlee.smartpotui.entities.api.TreeEntity;
import com.example.andrejlee.smartpotui.ui.bases.BasePresenter;

/**
 * Created by Andrej Lee on 11/12/2017.
 */

public class DetailTreePresenter extends BasePresenter<DetailTreeView> {

    public void getDeviceById(int id){
        SmartPotFacade.getInstance().getDeviceById(id)
                .subscribe(returnValue -> {
                    mMvpView.setContentSafeMoisture(String.valueOf(returnValue.getAttribute().getSaveValue()));
                    mMvpView.setContentCurrentMoisture(String.valueOf(returnValue.getAttribute().getHumidity()));
                    mMvpView.setContentStatus(returnValue.getStatus());
                }, throwable -> {
                    mMvpView.showDialogMessageAPI(throwable.toString());
                });
    }
}
