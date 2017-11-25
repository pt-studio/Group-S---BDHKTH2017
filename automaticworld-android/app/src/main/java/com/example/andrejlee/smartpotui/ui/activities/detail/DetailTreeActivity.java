package com.example.andrejlee.smartpotui.ui.activities.detail;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.constants.Constants;
import com.example.andrejlee.smartpotui.ui.activities.update_tree.UpdateTreeActivity;
import com.example.andrejlee.smartpotui.ui.bases.BaseDefaultActivity;
import com.github.mikephil.charting.charts.LineChart;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailTreeActivity extends BaseDefaultActivity implements DetailTreeView {

    @BindView(R.id.lineChart)
    LineChart mLineChart;
    @BindView(R.id.tv_safe_moisture)
    TextView mSafeMoisture;
    @BindView(R.id.tv_current_moisture)
    TextView mCurrentMoisture;
    @BindView(R.id.tv_status)
    TextView mStatus;
    @BindView(R.id.tv_edit_tree)
    TextView mEdit;

    private DetailTreePresenter mPresenter;
    private int mCurrentTreeId;

    public static void start(Activity context, int treeId) {
        Intent starter = new Intent(context, DetailTreeActivity.class);
        starter.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        starter.putExtra(Constants.STRING_TREE_ID_INTENT, treeId);
        context.startActivity(starter);
        context.overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContainer(R.layout.activity_detail_tree);
        ButterKnife.bind(this);
        initViews();
        mPresenter = new DetailTreePresenter();
        mPresenter.attachView(this);
    }

    private void initViews() {
        Intent intent = getIntent();

        if (intent != null) {
            mCurrentTreeId = intent.getIntExtra(Constants.STRING_TREE_ID_INTENT, Constants.NEGA_ONE_VALUE);
            if (mCurrentTreeId != Constants.NEGA_ONE_VALUE) {
                mPresenter.getDeviceById(mCurrentTreeId);
            }
        }

        initLineChart();
    }

    private void initLineChart() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
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

    @Override
    public void setContentSafeMoisture(String content) {
        mSafeMoisture.setText(content);
    }

    @Override
    public void setContentCurrentMoisture(String content) {
        mCurrentMoisture.setText(content);
    }

    @Override
    public void setContentStatus(String content) {
        mStatus.setText(content);
    }

    @OnClick(R.id.tv_edit_tree)
    public void onClick(View v) {
        UpdateTreeActivity.start(this, mCurrentTreeId);
    }
}
