package com.example.andrejlee.smartpotui.ui.fragments.change_pass;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.blankj.utilcode.util.KeyboardUtils;
import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.constants.Constants;
import com.example.andrejlee.smartpotui.ui.bases.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnFocusChange;


public class ChangePassFragment extends BaseFragment implements ChangePassFragmentView {

    @BindView(R.id.et_old_password)
    EditText mOldPass;
    @BindView(R.id.et_new_password)
    EditText mNewPass;
    @BindView(R.id.et_confirm_password)
    EditText mConfirmPass;


    public ChangePassFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_pass, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void showViewState(Constants.ViewState state) {

    }

    @Override
    public Context getCurrentContext() {
        return getContext();
    }

    @Override
    public AlertDialog getAlertDialog() {
        return mAlertDialog;
    }

    @OnFocusChange({R.id.et_old_password,R.id.et_new_password,R.id.et_confirm_password})
    public void onFocusChanged(View v, boolean hasFocus){
        if (hasFocus){
            KeyboardUtils.showSoftInput(getActivity());
        } else {
            KeyboardUtils.hideSoftInput(v);
        }
    }
}
