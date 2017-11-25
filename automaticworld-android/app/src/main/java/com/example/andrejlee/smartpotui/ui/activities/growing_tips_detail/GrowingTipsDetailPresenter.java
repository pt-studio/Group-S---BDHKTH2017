package com.example.andrejlee.smartpotui.ui.activities.growing_tips_detail;

import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.common.SmartPotFacade;
import com.example.andrejlee.smartpotui.ui.bases.BasePresenter;

public class GrowingTipsDetailPresenter extends BasePresenter<GrowingTipsDetailView> {

    public void getBlogById(int id){
        SmartPotFacade.getInstance().getBlogById(id)
                .subscribe(returnValue -> {
                    if (mMvpView != null){
                        switch (returnValue.getTitle()){
                            case "Hoa hồng":
                                mMvpView.setImageContent(R.drawable.tip_0);
                                break;
                            case "Cẩm nhung":
                                mMvpView.setImageContent(R.drawable.tip_1);
                                break;
                            case "Xương rồng":
                                mMvpView.setImageContent(R.drawable.tip_2);
                                break;
                        }

                        mMvpView.setTitleContent(returnValue.getTitle());
                        mMvpView.setDescriptionContent(returnValue.getContent());
                    }
                }, throwable -> {
                    if (mMvpView != null){
                        mMvpView.showDialogMessageAPI(throwable.toString());
                    }
                });
    }
}
