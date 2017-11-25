package com.example.andrejlee.smartpotui.ui.fragments.growing_tips;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.adapters.GrowTipsAdapter;
import com.example.andrejlee.smartpotui.common.SmartPotFacade;
import com.example.andrejlee.smartpotui.constants.Constants;
import com.example.andrejlee.smartpotui.entities.api.GrowTipEntity;
import com.example.andrejlee.smartpotui.ui.activities.growing_tips_detail.GrowingTipsDetailView;
import com.example.andrejlee.smartpotui.ui.bases.BaseFragment;
import com.example.andrejlee.smartpotui.ui.bases.BasePresenter;

import java.util.List;

public class GrowingTipsFragmentPresenter extends BasePresenter<GrowingTipsFragmentView> {

    public void loadData(GrowTipsAdapter mAdapter, LinearLayout mEmptyContainer) {
        SmartPotFacade.getInstance().getAllBlogs()
                .subscribe(returnValue -> {
                    if (mMvpView != null){
                        if (returnValue == null || returnValue.size() == Constants.ZERO_VALUE){
                            mEmptyContainer.setVisibility(View.VISIBLE);
                        } else {
                            mEmptyContainer.setVisibility(View.GONE);
                            setFakeData(returnValue);
                            mAdapter.setmListItem(returnValue);
                        }
                    }
                }, throwable -> {
                    if (mMvpView != null){
                        mMvpView.showDialogMessageAPI(throwable.toString());
                    }
                });
    }

    private void setFakeData(List<GrowTipEntity> mData){
        if (mData != null && mData.size() != 0) {
            for (int i = 0; i < mData.size(); i++){
                switch (i){
                    case 0:
                        mData.get(i).setImageId(R.drawable.tip_0);
                        break;
                    case 1:
                        mData.get(i).setImageId(R.drawable.tip_1);
                        break;
                    case 2:
                        mData.get(i).setImageId(R.drawable.tip_2);
                        break;
                    default:
                        mData.get(i).setImageId(R.drawable.tip_0);
                        break;
                }
            }
        }

    }
}
