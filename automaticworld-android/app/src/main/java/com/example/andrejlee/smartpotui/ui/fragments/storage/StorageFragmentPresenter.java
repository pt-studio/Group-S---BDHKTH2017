package com.example.andrejlee.smartpotui.ui.fragments.storage;

import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.common.SmartPotFacade;
import com.example.andrejlee.smartpotui.constants.Constants;
import com.example.andrejlee.smartpotui.entities.api.TreeEntity;
import com.example.andrejlee.smartpotui.ui.bases.BasePresenter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StorageFragmentPresenter extends BasePresenter<StorageFragmentView> {

    public void getAllDevice() {
        List<TreeEntity> tempList = new ArrayList<>();

        SmartPotFacade.getInstance().getAllDevices()
                .subscribe(returnValue -> {
                    calculate(returnValue);
                }, throwable -> {
                    if (mMvpView != null) {
                        mMvpView.showDialogMessageAPI(throwable.toString());
                    }
                });
    }

    private void calculate(List<TreeEntity> data) {
        if (mMvpView != null) {
            int countAvailPot = 0;
            int countUnavailPot = 0;
            int countAvailAuto = 0;
            int countUnavailAuto = 0;

            Iterator<TreeEntity> iterator = data.iterator();
            while (iterator.hasNext()) {
                TreeEntity currentTree = iterator.next();
                switch (currentTree.getDeviceType()) {
                    case Constants.DEVICE_TYPE_POT:
                        if (currentTree.getStatus().equals(mMvpView.getCurrentContext().getString(R.string.status_working_en))) {
                            countAvailPot++;
                        } else {
                            countUnavailPot++;
                        }
                        break;
                    case Constants.DEVICE_TYPE_AUTO:
                        if (currentTree.getStatus().equals(mMvpView.getCurrentContext().getString(R.string.status_working_en))) {
                            countAvailAuto++;
                        } else {
                            countUnavailAuto++;
                        }
                        break;
                }
            }

            mMvpView.setAvailPotContent(String.valueOf(countAvailPot));
            mMvpView.setUnavailPotContent(String.valueOf(countUnavailPot));
            mMvpView.setAvailAutoContent(String.valueOf(countAvailAuto));
            mMvpView.setUnvailAutoContent(String.valueOf(countUnavailAuto));

            mMvpView.setSmartPB((countAvailPot == countUnavailPot) ? 50 : countAvailPot * 100 / (countAvailPot + countUnavailPot));
            mMvpView.setAutoPB((countAvailAuto == countUnavailAuto) ? 50 : countAvailAuto * 100 / (countAvailAuto + countUnavailAuto));
        }
    }

    public void getTypeById(int id) {
        SmartPotFacade.getInstance().getTypeById(id)
                .subscribe(returnValue -> {

                }, throwable -> {

                });
    }
}
