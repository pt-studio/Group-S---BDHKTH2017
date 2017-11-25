package com.example.andrejlee.smartpotui.ui.fragments.add_tree;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
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
public class AddTreeFragment extends BaseFragment implements AddTreeFragmentView {

    @BindView(R.id.spinner_device)
    Spinner spinner;
    @BindView(R.id.et_tree_name)
    EditText mTreeName;
    @BindView(R.id.sb_moisture)
    SeekBar mMoisture;
    @BindView(R.id.tv_progress_value)
    TextView mProgessValue;
    @BindView(R.id.tv_create_tree)
    TextView mCreateTree;

    private AddTreeFragmentPresenter mPresenter;


    public AddTreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_tree, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        mPresenter = new AddTreeFragmentPresenter();
        mPresenter.attachView(this);
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

    private void initView() {
//        ArrayAdapter adapter = ArrayAdapter.createFromResource(getContext(), R.array.device_dropdown_items, R.layout.spinner_item);
//        spinner.setAdapter(adapter);

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

    @OnFocusChange(R.id.et_tree_name)
    public void onFocusChanged(View v, boolean hasFocus){
        if(!hasFocus){
            KeyboardUtils.hideSoftInput(v);
        } else {
            KeyboardUtils.showSoftInput(getActivity());
        }
    }

    @OnClick(R.id.tv_create_tree)
    public void onClick(View v){
        if (!mTreeName.getText().toString().equals(Constants.EMPTY_STRING) && !mProgessValue.getText().toString().equals(Constants.EMPTY_STRING)){
            int deviceType = (spinner.getSelectedItem().toString().equals(getString(R.string.store_smart_title)))? 1 : 2;
            mPresenter.addTree(mTreeName.getText().toString(), Integer.parseInt(mProgessValue.getText().toString()), deviceType);
        } else {
            showDialogMessageAPI(getString(R.string.missing_value));
        }
    }
}
