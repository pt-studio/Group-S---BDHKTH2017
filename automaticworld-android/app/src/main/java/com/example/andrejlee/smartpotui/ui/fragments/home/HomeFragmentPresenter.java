package com.example.andrejlee.smartpotui.ui.fragments.home;

import android.util.Log;

import com.example.andrejlee.smartpotui.common.SmartPotFacade;
import com.example.andrejlee.smartpotui.entities.api.TreeEntity;
import com.example.andrejlee.smartpotui.ui.bases.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrej Lee on 11/14/2017.
 */

public class HomeFragmentPresenter extends BasePresenter<HomeFragmentView> {

    public void getAllDevice(){
        List<TreeEntity> tempList = new ArrayList<>();

        SmartPotFacade.getInstance().getAllDevices()
                .subscribe(returnValue -> {
                    SmartPotFacade.getInstance().getDeviceById(returnValue.get(0).getId())
                            .subscribe(value -> {
                                Log.d("Temp", String.valueOf(value.getStatus()));
                            });
                }, throwable -> {
                    throwable.printStackTrace();
                });
    }
}
