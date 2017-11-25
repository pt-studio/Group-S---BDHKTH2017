package com.example.andrejlee.smartpotui.ui.activities.forgot_password;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.constants.Constants;
import com.example.andrejlee.smartpotui.ui.bases.BaseDefaultActivity;

public class ForgotPasswordActivity extends BaseDefaultActivity implements ForgotPasswordView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_passwod);
        showToolbar(false);
    }

    @Override
    public void showViewState(Constants.ViewState state) {

    }
}
