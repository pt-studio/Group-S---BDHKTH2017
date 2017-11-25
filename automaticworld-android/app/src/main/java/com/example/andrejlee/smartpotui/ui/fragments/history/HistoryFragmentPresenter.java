package com.example.andrejlee.smartpotui.ui.fragments.history;

import com.example.andrejlee.smartpotui.common.SmartPotFacade;
import com.example.andrejlee.smartpotui.ui.bases.BasePresenter;

/**
 * Created by Andrej Lee on 11/14/2017.
 */

public class HistoryFragmentPresenter extends BasePresenter<HistoryFragmentView> {

    public void getAllNotis(){
        SmartPotFacade.getInstance().getAllNotis()
                .subscribe(returnValue -> {

                }, throwable -> {

                });
    }
}
