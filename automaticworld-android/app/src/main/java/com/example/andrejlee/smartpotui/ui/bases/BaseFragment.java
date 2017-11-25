package com.example.andrejlee.smartpotui.ui.bases;

import android.app.AlertDialog;
import android.view.View;

import com.example.andrejlee.smartpotui.ui.dialogs.CommonDialogUtil;
import com.onetech.core.base.permission.FragmentPermissionManager;

import org.greenrobot.eventbus.EventBus;

import butterknife.Unbinder;


public class BaseFragment extends FragmentPermissionManager {

    protected EventBus mEventBus = EventBus.getDefault();
    protected Unbinder unbinder;
    protected AlertDialog mAlertDialog;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    public void showDialogMessageAPI(String content) {
        if (!CommonDialogUtil.sIsShowed) {
            mAlertDialog = CommonDialogUtil.showOkDialogAction(getActivity(), content
                    , v -> {
                        CommonDialogUtil.sIsShowed = false;
                        mAlertDialog.dismiss();
                    });
            CommonDialogUtil.sIsShowed = true;
        }

    }

    public void showDialogMessageAPIWithAction(String content, View.OnClickListener listener) {
        if (!CommonDialogUtil.sIsShowed) {
            mAlertDialog = CommonDialogUtil.showOkDialogAction(getActivity(), content
                    , listener);
            CommonDialogUtil.sIsShowed = true;
        }
    }

}
