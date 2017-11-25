package com.example.andrejlee.smartpotui.ui.fragments.home;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.adapters.TreeAdapter;
import com.example.andrejlee.smartpotui.constants.Constants;
import com.example.andrejlee.smartpotui.entities.api.TreeEntity;
import com.example.andrejlee.smartpotui.ui.activities.home.HomeActivity;
import com.example.andrejlee.smartpotui.ui.bases.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment implements HomeFragmentView {

    @BindView(R.id.gv_list_tree)
    RecyclerView mGridView;
    @BindView(R.id.btn_floating_action)
    android.support.design.widget.FloatingActionButton mFloatingBtn;

    private HomeFragmentPresenter mPresenter;
    private TreeAdapter mAdapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new HomeFragmentPresenter();
        mPresenter.attachView(this);
        initView();
        mPresenter.getAllDevice();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        mAdapter = new TreeAdapter(getContext(), R.layout.layout_grid_item);
        return view;
    }

    private void initView() {
        mGridView.setLayoutManager(new GridLayoutManager(getContext(), Constants.COLLUMN_NUM));
        mAdapter.setmListItem(new ArrayList<>());
        mGridView.setAdapter(mAdapter);
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
        return getContext();
    }

    @Override
    public AlertDialog getAlertDialog() {
        return mAlertDialog;
    }

    @OnClick(R.id.btn_floating_action)
    public void onClick(View view) {
        ((HomeActivity) getActivity()).replaceFragment(Constants.TabId.ADD);
    }

    @Override
    public void loadDataToGridView(List<TreeEntity> data) {
        mAdapter.setmListItem(data);
    }
}
