package com.example.andrejlee.smartpotui.ui.activities.change_password;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.constants.Constants;
import com.example.andrejlee.smartpotui.ui.bases.BaseDefaultActivity;

public class ChangePasswordActivity extends BaseDefaultActivity implements ChangePasswordView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
    }

    @Override
    public void showViewState(Constants.ViewState state) {

    }
}
