package com.example.andrejlee.smartpotui.utils;


import android.app.Activity;
import android.support.v7.widget.SearchView;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.example.andrejlee.smartpotui.R;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.subjects.BehaviorSubject;

public class RxSearch {
    public static Observable<String> fromSearchView(@NonNull final SearchView searchView, Activity currentActivity) {
        final BehaviorSubject<String> subject = BehaviorSubject.create();
        EditText searchPlate = (EditText) searchView.findViewById(R.id.search_src_text);

        searchPlate.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    KeyboardUtils.hideSoftInput(currentActivity);
                }
                return false;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                subject.onComplete();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                subject.onNext(newText);
                return true;
            }
        });

        return subject;
    }
}
