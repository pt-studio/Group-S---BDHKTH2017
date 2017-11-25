package com.example.andrejlee.smartpotui.ui.bases;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.example.andrejlee.smartpotui.R;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by HienNguyen on 7/5/2017.
 */

public abstract class BaseDefaultActivity extends BaseActivity implements View.OnClickListener {

    Toolbar mToolbar;
    View mDividerTop;
    FrameLayout mMainContainer;
    ImageView mMenu;
    ImageView mHome;
    ImageView mLogo;
    ImageView mSearch;
    ImageView mContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_default);
        bindView();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

    @Override
    public void onBackPressed() {
        closeActivity();
    }

    private void bindView() {
        mToolbar = ButterKnife.findById(this, R.id.toolbar);
        mDividerTop = ButterKnife.findById(this, R.id.divider_top);
        mMainContainer = ButterKnife.findById(this, R.id.main_container);
        mMenu = ButterKnife.findById(this, R.id.im_menu);
        mHome = ButterKnife.findById(this, R.id.im_home);
        mLogo = ButterKnife.findById(this, R.id.im_logo);
        mSearch = ButterKnife.findById(this, R.id.im_search);
        mContact = ButterKnife.findById(this, R.id.im_contact);
    }

    protected void setMainContainer(@LayoutRes int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null, false);
        mMainContainer.addView(view);
    }


    protected void showToolbar(boolean isShow) {
        mToolbar.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    protected void closeActivity() {
        KeyboardUtils.hideSoftInput(this);
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
