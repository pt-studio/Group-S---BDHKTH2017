package com.example.andrejlee.smartpotui.ui.fragments.growing_tips;

import com.example.andrejlee.smartpotui.common.SmartPotFacade;
import com.example.andrejlee.smartpotui.ui.activities.growing_tips_detail.GrowingTipsDetailView;
import com.example.andrejlee.smartpotui.ui.bases.BaseFragment;
import com.example.andrejlee.smartpotui.ui.bases.BasePresenter;

/**
 * Created by Andrej Lee on 11/14/2017.
 */

public class GrowingTipsFragmentPresenter extends BasePresenter<GrowingTipsFragmentView> {

    public void getAllBlogs(){
        SmartPotFacade.getInstance().getAllBlogs()
                .subscribe(returnValue -> {

                }, throwable -> {

                });
    }
}
