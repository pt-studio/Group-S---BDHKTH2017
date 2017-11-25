package com.example.andrejlee.smartpotui.ui.activities.update_tree;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.constants.Constants;
import com.example.andrejlee.smartpotui.ui.bases.BaseDefaultActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

public class UpdateTreeActivity extends BaseDefaultActivity implements UpdateTreeView {

    @BindView(R.id.et_tree_name_update)
    TextView mTreeName;
    @BindView(R.id.sb_moisture_update)
    SeekBar mMoisture;
    @BindView(R.id.tv_progress_value_update)
    TextView mProgessValue;
    @BindView(R.id.et_new_user)
    TextView mNewUser;
    @BindView(R.id.tv_update_btn)
    TextView mUpdate;
    @BindView(R.id.tv_turnoff_btn)
    TextView mTurnOff;

    private UpdateTreePresenter mPresenter;
    private int mCurrentTreeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContainer(R.layout.activity_update_tree);
        showToolbar(false);
        ButterKnife.bind(this);
        mPresenter = new UpdateTreePresenter();
        mPresenter.attachView(this);
        initView();
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
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    public static void start(Activity context, int treeId) {
        Intent starter = new Intent(context, UpdateTreeActivity.class);
        starter.putExtra(Constants.STRING_TREE_ID_INTENT, treeId);
        starter.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(starter);
        context.overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
    }

    private void initView() {
        Intent intent = getIntent();
        mCurrentTreeId = intent.getIntExtra(Constants.STRING_TREE_ID_INTENT, Constants.NEGA_ONE_VALUE);
        mPresenter.getDeviceById(mCurrentTreeId);

        mMoisture.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Auto-generated method stub
                mProgessValue.setText(String.valueOf(progress));
            }
        });
    }

    @OnClick({R.id.tv_update_btn, R.id.tv_turnoff_btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_update_btn:
                mPresenter.updateTree(mCurrentTreeId,
                        mTreeName.getText().toString(),
                        mNewUser.getText().toString(),
                        Integer.parseInt(mProgessValue.getText().toString()));
                break;
            case R.id.tv_turnoff_btn:
                showDialogMessageAPI(getResources().getString(R.string.dialog_update_inform_text));
                break;
        }
    }

    @OnFocusChange({R.id.et_tree_name_update, R.id.et_new_user})
    public void onFocusChanged(View v, boolean hasFocus){
        if (!hasFocus){
            KeyboardUtils.hideSoftInput(v);
        } else {
            KeyboardUtils.showSoftInput(this);
        }
    }

    @Override
    public void setContentTreeName(String name) {
        mTreeName.setText(name);
    }

    @Override
    public void setSeekBarProgress(int value) {
        mMoisture.setProgress(value);
    }

    @Override
    public void setContentProgressValue(String value) {
        mProgessValue.setText(value);
    }

    @Override
    public void setContentCurrentUser(String value) {
        mNewUser.setText(value);
    }
}
