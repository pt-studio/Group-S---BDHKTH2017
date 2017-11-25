package com.example.andrejlee.smartpotui.ui.fragments.update_tree;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.constants.Constants;
import com.example.andrejlee.smartpotui.ui.bases.BaseFragment;
import com.example.andrejlee.smartpotui.ui.fragments.home.HomeFragmentPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateTreeFragment extends BaseFragment implements UpdateTreeFragmentView {
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

    private UpdateTreeFragmentPresenter mPresenter;

    public UpdateTreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_tree, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        mPresenter = new UpdateTreeFragmentPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        mPresenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void showViewState(Constants.ViewState state) {

    }

    @Override
    public Context getCurrentContext() {
        return null;
    }

    @Override
    public AlertDialog getAlertDialog() {
        return null;
    }

    private void initView() {
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
                mProgessValue.setText(progress);
            }
        });
    }

    @OnClick({R.id.tv_update_btn, R.id.tv_turnoff_btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_update_btn:
                break;
            case R.id.tv_turnoff_btn:
                showDialogMessageAPI(getResources().getString(R.string.dialog_update_inform_text));
        }
    }

    @OnFocusChange({R.id.et_tree_name_update, R.id.et_new_user})
    public void onFocusChanged(View v, boolean hasFocus){
        if (!hasFocus){
            KeyboardUtils.hideSoftInput(v);
        } else {
            KeyboardUtils.showSoftInput(getActivity());
        }
    }
}
