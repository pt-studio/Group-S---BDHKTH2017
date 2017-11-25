package com.example.andrejlee.smartpotui.ui.activities.update_tree;

import com.example.andrejlee.smartpotui.common.SmartPotFacade;
import com.example.andrejlee.smartpotui.ui.bases.BasePresenter;

/**
 * Created by Andrej Lee on 11/17/2017.
 */

public class UpdateTreePresenter extends BasePresenter<UpdateTreeView> {

    public void updateTree(int id, String name, String username, int safeValue){
        SmartPotFacade.getInstance().updateTree(id, name, username, safeValue)
                .subscribe(returnValue -> {

                }, throwable -> {

                });
    }
}
