package com.example.andrejlee.smartpotui.ui.fragments.storage;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.constants.Constants;
import com.example.andrejlee.smartpotui.ui.bases.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class StorageFragment extends BaseFragment implements StorageFragmentView {

    @BindView(R.id.tv_avail_pots)
    TextView mAvailPots;
    @BindView(R.id.tv_unavail_pots)
    TextView mUnavailPots;
    @BindView(R.id.tv_avail_auto)
    TextView mAvailAuto;
    @BindView(R.id.tv_unavail_auto)
    TextView mUnavailAuto;
    @BindView(R.id.pb_smart_pots)
    ProgressBar mSmartPB;
    @BindView(R.id.pb_auto)
    ProgressBar mAutoPB;

    private StorageFragmentPresenter mPresenter;


    public StorageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_storage, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new StorageFragmentPresenter();
        mPresenter.attachView(this);
        initView();
        mPresenter.getAllDevice();
    }

    private void initView() {

    }

    @Override
    public void showViewState(Constants.ViewState state) {

    }

    @Override
    public void onDestroyView() {
        mPresenter.detachView();
        super.onDestroyView();
    }

    @Override
    public Context getCurrentContext() {
        return getContext();
    }

    @Override
    public AlertDialog getAlertDialog() {
        return mAlertDialog;
    }

    @Override
    public void setAvailPotContent(String s) {
        mAvailPots.setText(s);
    }

    @Override
    public void setUnavailPotContent(String s) {
        mUnavailPots.setText(s);
    }

    @Override
    public void setAvailAutoContent(String s) {
        mAvailAuto.setText(s);
    }

    @Override
    public void setUnvailAutoContent(String s) {
        mUnavailAuto.setText(s);
    }

    @Override
    public void setSmartPB(int i) {
        mSmartPB.setProgress(i);
    }

    @Override
    public void setAutoPB(int i) {
        mAutoPB.setProgress(i);
    }
}
