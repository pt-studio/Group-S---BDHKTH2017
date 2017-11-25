package com.example.andrejlee.smartpotui.ui.fragments.history;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.adapters.GrowTipsAdapter;
import com.example.andrejlee.smartpotui.adapters.NotiAdapter;
import com.example.andrejlee.smartpotui.constants.Constants;
import com.example.andrejlee.smartpotui.entities.api.NotiEntity;
import com.example.andrejlee.smartpotui.ui.bases.BaseFragment;
import com.marshalchen.ultimaterecyclerview.layoutmanagers.ScrollSmoothLineaerLayoutManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends BaseFragment implements HistoryFragmentView {

    @BindView(R.id.rv_history)
    RecyclerView mRecycler;
    @BindView(R.id.empty_container_history)
    LinearLayout mEmptyContainer;

    private HistoryFragmentPresenter mPresenter;
    private NotiAdapter mAdapter;


    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new HistoryFragmentPresenter();
        mPresenter.attachView(this);
        initView();
        mPresenter.getAllNotis();
    }

    @Override
    public void onDestroyView() {
        mPresenter.detachView();
        super.onDestroyView();
    }

    private void initView() {
        final int LAYOUT_DURATION = 50;
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new ScrollSmoothLineaerLayoutManager(getContext(), LinearLayoutManager
                .VERTICAL, false, LAYOUT_DURATION));
        mAdapter = new NotiAdapter(getContext(), R.layout.layout_noti_item);
        mRecycler.setAdapter(mAdapter);
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

    @Override
    public void updateData(List<NotiEntity> result) {
        if (result != null) {
            mAdapter.setmListItem(result);
        }
    }

    @Override
    public void showEmptyLayout(boolean isShow) {
        if (isShow){
            mEmptyContainer.setVisibility(View.VISIBLE);
        } else {
            mEmptyContainer.setVisibility(View.GONE);
        }
    }
}
