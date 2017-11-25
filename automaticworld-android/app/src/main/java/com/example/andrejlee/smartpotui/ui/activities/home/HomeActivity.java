package com.example.andrejlee.smartpotui.ui.activities.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.constants.Constants;
import com.example.andrejlee.smartpotui.ui.bases.BaseDefaultActivity;
import com.example.andrejlee.smartpotui.ui.fragments.about.AboutFragment;
import com.example.andrejlee.smartpotui.ui.fragments.add_tree.AddTreeFragment;
import com.example.andrejlee.smartpotui.ui.fragments.change_pass.ChangePassFragment;
import com.example.andrejlee.smartpotui.ui.fragments.growing_tips.GrowingTipsFragment;
import com.example.andrejlee.smartpotui.ui.fragments.history.HistoryFragment;
import com.example.andrejlee.smartpotui.ui.fragments.home.HomeFragment;
import com.example.andrejlee.smartpotui.ui.fragments.storage.StorageFragment;
import com.example.andrejlee.smartpotui.ui.fragments.update_tree.UpdateTreeFragment;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseDefaultActivity implements HomeView {


    @BindView(R.id.main_container)
    FrameLayout mMainContainer;
    private HomePresenter mPresenter;
    private Map<String, Fragment> mFragmentManager;

    public static void start(Activity context) {
        Intent starter = new Intent(context, HomeActivity.class);
        starter.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(starter);
        context.overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContainer(R.layout.activity_home);
        showToolbar(true);
        ButterKnife.bind(this);
        initPresenter();
        mFragmentManager = new HashMap<>();
        replaceFragment(Constants.TabId.HOME);
    }

    public void replaceFragment(@Constants.TabId int id) {
        Fragment fragment = null;
        switch (id) {
            case Constants.TabId.HOME:
                if (mFragmentManager.containsKey(HomeFragment.class.getName())) {
                    fragment = mFragmentManager.get(HomeFragment.class.getName());
                }
                if (fragment == null) {
                    fragment = new HomeFragment();
                    mFragmentManager.put(HomeFragment.class.getName(), fragment);
                }
                break;
            case Constants.TabId.ADD:
                if (mFragmentManager.containsKey(AddTreeFragment.class.getName())) {
                    fragment = mFragmentManager.get(AddTreeFragment.class.getName());
                }
                if (fragment == null) {
                    fragment = new AddTreeFragment();
                    mFragmentManager.put(AddTreeFragment.class.getName(), fragment);
                }
                break;
            case Constants.TabId.TIPS:
                if (mFragmentManager.containsKey(GrowingTipsFragment.class.getName())) {
                    fragment = mFragmentManager.get(GrowingTipsFragment.class.getName());
                }
                if (fragment == null) {
                    fragment = new GrowingTipsFragment();
                    mFragmentManager.put(GrowingTipsFragment.class.getName(), fragment);
                }
                break;
            case Constants.TabId.HISTORY:
                if (mFragmentManager.containsKey(HistoryFragment.class.getName())) {
                    fragment = mFragmentManager.get(HistoryFragment.class.getName());
                }
                if (fragment == null) {
                    fragment = new HistoryFragment();
                    mFragmentManager.put(HistoryFragment.class.getName(), fragment);
                }
                break;
            case Constants.TabId.UPDATE:
                if (mFragmentManager.containsKey(UpdateTreeFragment.class.getName())) {
                    fragment = mFragmentManager.get(UpdateTreeFragment.class.getName());
                }
                if (fragment == null) {
                    fragment = new UpdateTreeFragment();
                    mFragmentManager.put(UpdateTreeFragment.class.getName(), fragment);
                }
                break;
            case Constants.TabId.CHANGEPASS:
                if (mFragmentManager.containsKey(ChangePassFragment.class.getName())) {
                    fragment = mFragmentManager.get(ChangePassFragment.class.getName());
                }
                if (fragment == null) {
                    fragment = new ChangePassFragment();
                    mFragmentManager.put(ChangePassFragment.class.getName(), fragment);
                }
                break;
            case Constants.TabId.STORAGE:
                if (mFragmentManager.containsKey(StorageFragment.class.getName())) {
                    fragment = mFragmentManager.get(StorageFragment.class.getName());
                }
                if (fragment == null) {
                    fragment = new StorageFragment();
                    mFragmentManager.put(StorageFragment.class.getName(), fragment);
                }
                break;
            case Constants.TabId.ABOUT:
                if (mFragmentManager.containsKey(AboutFragment.class.getName())) {
                    fragment = mFragmentManager.get(AboutFragment.class.getName());
                }
                if (fragment == null) {
                    fragment = new AboutFragment();
                    mFragmentManager.put(AboutFragment.class.getName(), fragment);
                }
                break;
        }
//
//        // Animation when back to smaller id fragments
//        boolean back = id > mSelectedTab ? false : true;
        replaceFragment(fragment);
//
//        // Update selected tab
//        mSelectedTab = id;
    }

    private void replaceFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
//            if (isAnimate) {
//                if (back) {
//                    transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
//                } else {
//                    transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
//                }
//            }
            transaction.replace(R.id.main_container, fragment, fragment.getClass().getName());
            transaction.commit();
        }
    }

    private void initPresenter() {
        mPresenter = new HomePresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void showViewState(Constants.ViewState state) {

    }

    @Override
    public Context getCurrentContext() {
        return this;
    }


    @Override
    public AlertDialog getAlertDialog() {
        return mAlertDialog;
    }

    @Override
    protected void onLogoutItemClick() {
    }

    @Override
    protected void onPasswordItemClick(IDrawerItem drawerItem) {
//        mDrawer.setSelection(drawerItem);
        mDrawer.closeDrawer();
        replaceFragment(Constants.TabId.CHANGEPASS);
    }

    @Override
    protected void onAboutItemClick(IDrawerItem drawerItem) {
//        mDrawer.setSelection(drawerItem);
        mDrawer.closeDrawer();
        replaceFragment(Constants.TabId.ABOUT);
    }

    @Override
    protected void onHistoryItemClick(IDrawerItem drawerItem) {
//        mDrawer.setSelection(drawerItem);
        mDrawer.closeDrawer();
        replaceFragment(Constants.TabId.HISTORY);

    }

    @Override
    protected void onStorageItemClick(IDrawerItem drawerItem) {
//        mDrawer.setSelection(drawerItem);
        mDrawer.closeDrawer();
        replaceFragment(Constants.TabId.STORAGE);
    }

    @Override
    protected void onGrowingTipsItemClick(IDrawerItem drawerItem) {
//        mDrawer.setSelection(drawerItem);
        mDrawer.closeDrawer();
        replaceFragment(Constants.TabId.TIPS);
    }

    @Override
    protected void onAddTreeItemClick(IDrawerItem drawerItem) {
//        mDrawer.setSelection(drawerItem);
        mDrawer.closeDrawer();
        replaceFragment(Constants.TabId.ADD);
    }

    @Override
    protected void onHeaderHomeClick() {
        mDrawer.closeDrawer();
        replaceFragment(Constants.TabId.HOME);
    }
}
