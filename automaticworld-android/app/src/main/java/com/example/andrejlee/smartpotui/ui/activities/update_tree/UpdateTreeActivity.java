package com.example.andrejlee.smartpotui.ui.activities.update_tree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.constants.Constants;
import com.example.andrejlee.smartpotui.ui.bases.BaseDefaultActivity;

public class UpdateTreeActivity extends BaseDefaultActivity implements UpdateTreeView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tree);
    }

    @Override
    public void showViewState(Constants.ViewState state) {

    }
}
