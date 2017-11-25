package com.example.andrejlee.smartpotui.ui.activities.forgot_password;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.constants.Constants;
import com.example.andrejlee.smartpotui.ui.bases.BaseDefaultActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnFocusChange;

public class ForgotPasswordActivity extends BaseDefaultActivity implements ForgotPasswordView {

    @BindView(R.id.et_forgot_email_label)
    EditText mEmail;
    @BindView(R.id.tv_send_email)
    TextView mSendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContainer(R.layout.activity_forgot_passwod);
        showToolbar(false);
        ButterKnife.bind(this);
    }

    @Override
    public void showViewState(Constants.ViewState state) {

    }

    @Override
    public Context getCurrentContext() {
        return this;
    }


    @Override
    public AlertDialog getAlertDialog() {
        return mAlertDialog;
    }

    public static void start(Activity context) {
        Intent starter = new Intent(context, ForgotPasswordActivity.class);
        starter.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(starter);
        context.overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
    }

    @OnFocusChange(R.id.et_forgot_email_label)
    public void onFocusChanged(View v, boolean hasFocus){
        if (!hasFocus){
            KeyboardUtils.hideSoftInput(v);
        } else {
            KeyboardUtils.showSoftInput(this);
        }
    }
}
