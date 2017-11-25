package com.example.andrejlee.smartpotui.adapters;

import android.content.Context;

import com.example.andrejlee.smartpotui.constants.Constants;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends UltimateViewAdapter {

    protected Context mContext;
    protected List<T> mItems;

    // CONSTRUCTOR
    public BaseAdapter(Context _context) {
        mContext = _context;
        initItems();
    }

    // Override Methods
    @Override
    public int getItemCount() {
        if (mItems == null) {
            return 0;
        }
        return mItems.size();
    }

    // Protected Methods
    protected void initItems() {
        if (mItems == null) {
            mItems = new ArrayList<>();
        }
    }

    // PUBLIC METHODS
    public T getItem(int position) {
        if (mItems != null && position > Constants.NEGA_ONE_VALUE && position < mItems.size()) {
            return mItems.get(position);
        }
        return null;
    }

    public List<T> getItems() {
        return mItems;
    }

    public void update(List<T> data) {
        removeAllInternal(mItems);
        insertInternal(data, mItems);
    }

    public void insert(List<T> new_data) {
        insertInternal(new_data, mItems);
    }
}
