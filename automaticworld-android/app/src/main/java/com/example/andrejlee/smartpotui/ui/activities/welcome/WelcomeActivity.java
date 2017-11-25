package com.example.andrejlee.smartpotui.ui.activities.welcome;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.constants.Constants;
import com.example.andrejlee.smartpotui.ui.bases.BaseDefaultActivity;

public class WelcomeActivity extends BaseDefaultActivity implements WelcomeActivityView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        showToolbar(false);
    }

    @Override
    public void showViewState(Constants.ViewState state) {

    }
}
