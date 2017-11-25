package com.example.andrejlee.smartpotui.ui.activities.growing_tips_detail;

import com.example.andrejlee.smartpotui.common.SmartPotFacade;
import com.example.andrejlee.smartpotui.ui.bases.BasePresenter;

/**
 * Created by Andrej Lee on 11/12/2017.
 */

public class GrowingTipsDetailPresenter extends BasePresenter<GrowingTipsDetailView> {

    public void getBlogById(int id){
        SmartPotFacade.getInstance().getBlogById(id)
                .subscribe(returnValue -> {

                }, throwable -> {

                });
    }
}
