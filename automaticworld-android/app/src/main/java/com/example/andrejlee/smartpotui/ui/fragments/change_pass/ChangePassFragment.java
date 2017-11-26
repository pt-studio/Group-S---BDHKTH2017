package com.example.andrejlee.smartpotui.ui.fragments.change_pass;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.constants.Constants;
import com.example.andrejlee.smartpotui.ui.bases.BaseFragment;
import com.example.andrejlee.smartpotui.ui.fragments.storage.StorageFragmentPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;


public class ChangePassFragment extends BaseFragment implements ChangePassFragmentView {

    @BindView(R.id.et_old_password)
    EditText mOldPass;
    @BindView(R.id.et_new_password)
    EditText mNewPass;
    @BindView(R.id.et_confirm_password)
    EditText mConfirmPass;
    @BindView(R.id.tv_change_pass_btn)
    TextView mChangePassBtn;

    private ChangePassFragmentPresenter mPresenter;


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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new ChangePassFragmentPresenter();
        mPresenter.attachView(this);
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

    @OnClick(R.id.tv_change_pass_btn)
    public void onClick(View v){
        if (mOldPass.getText() == null || mOldPass.getText().toString().equals(Constants.EMPTY_STRING)
                || mNewPass.getText() == null || mNewPass.getText().toString().equals(Constants.EMPTY_STRING)
                || mConfirmPass.getText() == null || mConfirmPass.getText().toString().equals(Constants.EMPTY_STRING)){
            showDialogMessageAPI(getString(R.string.missing_value));
        } else {
            showDialogMessageAPI("Đổi mật khẩu thành công!");
        }
    }
}
