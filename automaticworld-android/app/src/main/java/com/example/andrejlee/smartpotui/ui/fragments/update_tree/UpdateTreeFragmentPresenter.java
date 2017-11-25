package com.example.andrejlee.smartpotui.ui.fragments.update_tree;

import com.example.andrejlee.smartpotui.common.SmartPotFacade;
import com.example.andrejlee.smartpotui.entities.api.AttributeEnity;
import com.example.andrejlee.smartpotui.ui.bases.BasePresenter;

/**
 * Created by Andrej Lee on 11/14/2017.
 */

public class UpdateTreeFragmentPresenter extends BasePresenter<UpdateTreeFragmentView> {

    public void updateTree(int id, String name, String username, int safeValue){
        SmartPotFacade.getInstance().updateTree(id, name, username, safeValue)
                .subscribe(returnValue -> {

                }, throwable -> {

                });
    }
}
