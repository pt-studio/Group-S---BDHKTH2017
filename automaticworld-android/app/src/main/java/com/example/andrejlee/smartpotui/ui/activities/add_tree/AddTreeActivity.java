package com.example.andrejlee.smartpotui.ui.activities.add_tree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.constants.Constants;
import com.example.andrejlee.smartpotui.ui.bases.BaseDefaultActivity;

import butterknife.BindView;

public class AddTreeActivity extends BaseDefaultActivity implements AddTreeView {

    @BindView(R.id.spinner_device)
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tree);
        initViews();
    }

    private void initViews() {
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.device_dropdown_items, R.layout.spinner_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void showViewState(Constants.ViewState state) {

    }
}
