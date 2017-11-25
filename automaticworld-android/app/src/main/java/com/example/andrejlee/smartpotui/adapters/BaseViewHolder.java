package com.example.andrejlee.smartpotui.adapters;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by thienlm on 7/27/2016.
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    // CONSTRUCTOR

    // Override Methods

    // PRIVATE METHODS

    // Protected Methods
    protected BaseViewHolder(ViewGroup parent, @LayoutRes int itemLayoutId) {
        super(LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent, false));
        ButterKnife.bind(this, itemView);
    }

    // PUBLIC METHODS
    public abstract void onBind(T t);
}
