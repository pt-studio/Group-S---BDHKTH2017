package com.example.andrejlee.smartpotui.ui.fragments.history;

import android.util.Log;

import com.example.andrejlee.smartpotui.common.SmartPotFacade;
import com.example.andrejlee.smartpotui.entities.api.NotiEntity;
import com.example.andrejlee.smartpotui.entities.api.TreeEntity;
import com.example.andrejlee.smartpotui.ui.bases.BasePresenter;

import java.util.Iterator;
import java.util.List;

import io.reactivex.Observable;

public class HistoryFragmentPresenter extends BasePresenter<HistoryFragmentView> {

    private List<NotiEntity> mNotiList;

    public void getAllNotis() {
        SmartPotFacade.getInstance().getAllNotis()
                .subscribe(returnValue -> {
                    mNotiList = returnValue;
                    Observable.fromIterable(mNotiList)
                            .flatMap(value -> SmartPotFacade.getInstance().getDeviceById(value.getUserDevice()))
                            .toList()
                            .subscribe(result -> {
                                Iterator iterator = result.iterator();
                                int i = 0;
                                while (iterator.hasNext()){
                                    mNotiList.get(i++).setTreeName(((TreeEntity)iterator.next()).getName());
                                }
                                if (mMvpView != null){
                                    if (mNotiList != null && mNotiList.size() != 0){
                                        mMvpView.updateData(mNotiList);
                                        mMvpView.showEmptyLayout(false);
                                    } else {
                                        mMvpView.showEmptyLayout(true);
                                    }
                                }
                            });

                }, throwable -> {
                    if (mMvpView != null) {
                        mMvpView.showDialogMessageAPI(throwable.toString());
                    }
                });
    }

//    public NotiEntity getDeviceById(NotiEntity data) {
//        if (data != null) {
//            SmartPotFacade.getInstance().getDeviceById(data.getUserDevice())
//                    .subscribe(returnValue -> {
//                    });
//        }
//    }
}
