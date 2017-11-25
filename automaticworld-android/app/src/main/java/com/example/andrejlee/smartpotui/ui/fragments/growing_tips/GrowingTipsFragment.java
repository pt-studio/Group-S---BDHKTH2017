package com.example.andrejlee.smartpotui.ui.fragments.growing_tips;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.adapters.GrowTipsAdapter;
import com.example.andrejlee.smartpotui.constants.Constants;
import com.example.andrejlee.smartpotui.ui.bases.BaseFragment;
import com.example.andrejlee.smartpotui.utils.RxSearch;
import com.example.andrejlee.smartpotui.utils.ViewUtils;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.marshalchen.ultimaterecyclerview.layoutmanagers.ScrollSmoothLineaerLayoutManager;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class GrowingTipsFragment extends BaseFragment implements GrowingTipsFragmentView {

    @BindView(R.id.search_view)
    SearchView mSearch;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.empty_container_setting)
    LinearLayout mEmptyContainer;
    @BindView(R.id.pullView)
    TwinklingRefreshLayout mRecyclerRefreshLayout;

    private GrowingTipsFragmentPresenter mPresenter;
    private GrowTipsAdapter mAdapter;

    public GrowingTipsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_growing_tips, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new GrowingTipsFragmentPresenter();
        mPresenter.attachView(this);
        initView();
    }

    @Override
    public void onStart() {
        super.onStart();
        final int WAITING_TYPING_TIME = 400;
        RxSearch.fromSearchView(mSearch, getActivity())
                .debounce(WAITING_TYPING_TIME, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(query ->
                        mAdapter.getFilter().filter(query, new Filter.FilterListener() {
                            public void onFilterComplete(int count) {
                                if (count == 0) {
                                    mEmptyContainer.setVisibility(View.VISIBLE);
                                } else {
                                    mEmptyContainer.setVisibility(View.GONE);
                                }
                            }
                        }));
        mPresenter.loadData(mAdapter, mEmptyContainer);
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
        mAdapter = new GrowTipsAdapter(getContext(), R.layout.layout_search_item);
        mRecycler.setAdapter(mAdapter);
        mRecyclerRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                // When pull to refresh, Call API to reload data, after 2s, finish refreshing
                KeyboardUtils.hideSoftInput(getActivity());
                eraseSearchView();
                mPresenter.loadData(mAdapter, mEmptyContainer);
                Observable.timer(Constants.REFRESH_DURATION, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(aLong -> {
                            if (mRecyclerRefreshLayout != null) {
                                mRecyclerRefreshLayout.finishRefreshing();
                            }
                        });
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                Observable.timer(Constants.REFRESH_DURATION, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(aLong -> {
                            if (mRecyclerRefreshLayout != null) {
                                mRecyclerRefreshLayout.finishLoadmore();
                            }
                        });
            }
        });

//        int id = mSearch.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
//        TextView searchTextVIew = (TextView) mSearch.findViewById(id);
//        searchTextVIew.setTextColor(Color.WHITE);
//
        final TextView searchTextVIew = (TextView) mSearch.findViewById(
                android.support.v7.appcompat.R.id.search_src_text);
        searchTextVIew.setTextColor(ContextCompat.getColor(getContext(), R.color.gray_text_hint_color));
        searchTextVIew.setHintTextColor(ContextCompat.getColor(getContext(), R.color.gray_text_hint_color));
        searchTextVIew.setTextSize(Constants.SEARCH_TEXT_SIZE);
        searchTextVIew.setPadding(Constants.SearchTextPadding.LEFT,
                Constants.SearchTextPadding.TOP,
                Constants.SearchTextPadding.RIGHT,
                Constants.SearchTextPadding.BOTTOM);
        searchTextVIew.setGravity(Gravity.CENTER);
        mSearch.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        ViewUtils.renderImageForHintTextView(getContext(), searchTextVIew, Constants.SEARCH_HINT_TEXT, R.drawable.ic_search_gray);
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

    private void eraseSearchView() {
        final TextView searchTextVIew = (TextView) mSearch.findViewById(
                android.support.v7.appcompat.R.id.search_src_text);
        searchTextVIew.setText(Constants.EMPTY_STRING);
    }
}
