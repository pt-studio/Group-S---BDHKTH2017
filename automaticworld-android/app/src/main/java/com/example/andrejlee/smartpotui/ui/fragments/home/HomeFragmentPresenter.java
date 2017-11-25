package com.example.andrejlee.smartpotui.ui.fragments.home;

import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.common.SmartPotFacade;
import com.example.andrejlee.smartpotui.entities.api.TreeEntity;
import com.example.andrejlee.smartpotui.ui.bases.BasePresenter;

import java.util.Iterator;
import java.util.List;

public class HomeFragmentPresenter extends BasePresenter<HomeFragmentView> {

    public void getAllDevice() {
        SmartPotFacade.getInstance().getAllDevices()
                .subscribe(returnValue -> {
                    if (mMvpView != null) {
                        mMvpView.loadDataToGridView(filterWorkingTrees(returnValue));
                    }
                }, throwable -> {
                    if (mMvpView != null) {
                        mMvpView.showDialogMessageAPI(throwable.toString());
                    }
                });
    }

    private List<TreeEntity> filterWorkingTrees(List<TreeEntity> data) {
        Iterator<TreeEntity> iterator = data.iterator();
        while (iterator.hasNext()) {
            TreeEntity currentTree = iterator.next();
            if (!currentTree.getStatus().equals(mMvpView.getCurrentContext().getString(R.string.status_working_en))) {
                iterator.remove();
            }
        }
        return data;
    }
}
