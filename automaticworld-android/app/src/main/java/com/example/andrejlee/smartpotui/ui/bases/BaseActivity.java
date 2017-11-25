package com.example.andrejlee.smartpotui.ui.bases;


import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

import com.example.andrejlee.smartpotui.ui.dialogs.CommonDialogUtil;
import com.onetech.core.base.permission.ActivityPermissionManager;

import org.greenrobot.eventbus.EventBus;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by ThienLee on 17-Apr-17.
 */

public abstract class BaseActivity extends ActivityPermissionManager {

    protected EventBus mEventBus = EventBus.getDefault();
    protected AlertDialog mAlertDialog;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void showDialogMessageAPI(String content) {
        if (!CommonDialogUtil.sIsShowed){
            mAlertDialog = CommonDialogUtil.showOkDialogAction(this, content
                    , v -> {
                        CommonDialogUtil.sIsShowed = false;
                        mAlertDialog.dismiss();
                    });
            CommonDialogUtil.sIsShowed = true;
        }
    }

    public void showDialogMessageAPIWithAction(String content, View.OnClickListener listener) {
        if (!CommonDialogUtil.sIsShowed) {
            mAlertDialog = CommonDialogUtil.showOkDialogAction(this, content
                    , listener);
            CommonDialogUtil.sIsShowed = true;
        }
    }
}
