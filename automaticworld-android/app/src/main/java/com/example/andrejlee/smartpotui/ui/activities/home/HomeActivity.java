package com.example.andrejlee.smartpotui.ui.activities.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.constants.Constants;
import com.example.andrejlee.smartpotui.ui.bases.BaseDefaultActivity;

public class HomeActivity extends BaseDefaultActivity implements HomeView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public void showViewState(Constants.ViewState state) {

    }
}
