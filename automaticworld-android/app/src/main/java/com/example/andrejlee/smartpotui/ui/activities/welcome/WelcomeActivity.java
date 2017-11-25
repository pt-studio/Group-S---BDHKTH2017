package com.example.andrejlee.smartpotui.ui.activities.welcome;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.constants.Constants;
import com.example.andrejlee.smartpotui.ui.activities.forgot_password.ForgotPasswordActivity;
import com.example.andrejlee.smartpotui.ui.activities.home.HomeActivity;
import com.example.andrejlee.smartpotui.ui.bases.BaseDefaultActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class WelcomeActivity extends BaseDefaultActivity implements WelcomeActivityView {

    @BindView(R.id.et_username)
    EditText mUsernameField;
    @BindView(R.id.et_password)
    EditText mPasswordField;
    @BindView(R.id.tv_login)
    TextView mLoginBtn;
    @BindView(R.id.tv_forgot)
    TextView mForgotBtn;
    @BindView(R.id.splash_container)
    FrameLayout mSplashContainer;
    @BindView(R.id.tv_logo_splash)
    TextView mLogoSplash;
    private WelcomeActivityPresenter mPresenter;
    private Animation mAnimationOut;
    private Animation mAnimationIn;

    public static void start(Activity context) {
        Intent starter = new Intent(context, WelcomeActivity.class);
        starter.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(starter);
        context.overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContainer(R.layout.activity_welcome);
        showToolbar(false);
        ButterKnife.bind(this);
        initPresenter();
        initAnimation();
        startAnimation();
    }

    private void initAnimation() {
        mAnimationOut = AnimationUtils.loadAnimation(this, R.anim.center_zoom_out_);
        mAnimationIn = AnimationUtils.loadAnimation(this, R.anim.center_zoom_in);

        mAnimationOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mSplashContainer.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mAnimationIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLogoSplash.startAnimation(mAnimationOut);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    private void initPresenter() {
        mPresenter = new WelcomeActivityPresenter();
        mPresenter.attachView(this);
    }

    private void startAnimation() {
        Observable.timer(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> mLogoSplash.startAnimation(mAnimationIn));
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

    @OnClick({R.id.tv_login, R.id.tv_forgot})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                HomeActivity.start(this);
                break;
            case R.id.tv_forgot:
                ForgotPasswordActivity.start(this);
                break;
        }
    }

    @OnFocusChange({R.id.et_username, R.id.et_password})
    public void onFocusChanged(View v, boolean hasFocus) {
        if (!hasFocus) {
            KeyboardUtils.hideSoftInput(v);
        } else {
            KeyboardUtils.showSoftInput(this);
        }
    }
}
