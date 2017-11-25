package com.example.andrejlee.smartpotui.ui.fragments.light;

import com.example.andrejlee.smartpotui.common.SmartPotFacade;
import com.example.andrejlee.smartpotui.ui.bases.BasePresenter;

/**
 * Created by Andrej Lee on 11/25/2017.
 */

public class LightTabPresenter extends BasePresenter<LightTabView> {

    public void getDeviceById(int id) {
        SmartPotFacade.getInstance().getDeviceById(id)
                .subscribe(returnValue -> {
                    if (mMvpView != null) {
//                        mMvpView.setContentSafeMoisture(String.valueOf(returnValue.getAttribute().getSaveValue()) + "/100");
//                        mMvpView.setContentCurrentMoisture(String.valueOf(returnValue.getAttribute().getHumidity()) + "/100");
                        mMvpView.setContentCurrentLight("50/100");
                        mMvpView.setContentSafeLightUpper("65/100");
                        mMvpView.setContentSafeLightLower("25/100");
                        mMvpView.setContentStatus(returnValue.getStatus().equals("working") ? "Hoạt động" : "Không hoạt động");
                    }
                }, throwable -> {
                    if (mMvpView != null) {
                        mMvpView.showDialogMessageAPI(throwable.toString());
                    }
                });
    }
}
