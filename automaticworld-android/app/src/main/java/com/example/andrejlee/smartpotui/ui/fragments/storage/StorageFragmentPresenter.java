package com.example.andrejlee.smartpotui.ui.fragments.storage;

import android.util.Log;

import com.example.andrejlee.smartpotui.common.SmartPotFacade;
import com.example.andrejlee.smartpotui.entities.api.TreeEntity;
import com.example.andrejlee.smartpotui.ui.bases.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrej Lee on 11/14/2017.
 */

public class StorageFragmentPresenter extends BasePresenter<StorageFragmentView> {

    public void getAllDevice(){
        List<TreeEntity> tempList = new ArrayList<>();

        SmartPotFacade.getInstance().getAllDevices()
                .subscribe(returnValue -> {

                }, throwable -> {
                    throwable.printStackTrace();
                });
    }

    public void getTypeById(int id){
        SmartPotFacade.getInstance().getTypeById(id)
                .subscribe(returnValue -> {

                }, throwable -> {

                });
    }
}
