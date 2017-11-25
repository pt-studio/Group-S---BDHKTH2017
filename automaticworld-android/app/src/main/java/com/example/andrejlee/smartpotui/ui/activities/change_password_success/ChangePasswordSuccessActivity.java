package com.example.andrejlee.smartpotui.ui.activities.change_password_success;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.KeyboardUtils;
import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.ui.bases.BaseDefaultActivity;

public class ChangePasswordSuccessActivity extends BaseDefaultActivity {

    public static void start(Activity context) {
        Intent starter = new Intent(context, ChangePasswordSuccessActivity.class);
        starter.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(starter);
        context.overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContainer(R.layout.activity_change_password_success);
        KeyboardUtils.hideSoftInput(this);
    }

}
