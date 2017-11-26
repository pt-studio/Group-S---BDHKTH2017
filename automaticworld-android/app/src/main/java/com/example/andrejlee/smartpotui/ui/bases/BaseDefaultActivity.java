package com.example.andrejlee.smartpotui.ui.bases;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.ui.activities.home.HomeActivity;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public abstract class BaseDefaultActivity extends BaseActivity implements View.OnClickListener {

    protected Drawer mDrawer;
    Toolbar mToolbar;
    //    View mDividerTop;
    FrameLayout mMainContainer;
    ImageView mMenu;
    ImageView mHome;
    ImageView mLogo;
    ImageView mSearch;
    ImageView mContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_default);
        KeyboardUtils.hideSoftInput(this);
        bindView();
        initNavigationDrawer();
    }

    private void initNavigationDrawer() {
        // Add Account Header
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.nav_menu_header_bg)
                .addProfiles(
                        new ProfileDrawerItem().withName("Lê Minh Sơn")
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        //if you want to update the items at a later time it is recommended to keep it in a variable
        //PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("item1");
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(1).withName(getResources().getString(R.string.nav_item_add_tree)).withIcon(FontAwesome.Icon.faw_plus);
        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withIdentifier(2).withName(getResources().getString(R.string.nav_item_growing_tips)).withIcon(FontAwesome.Icon.faw_pagelines);
        SecondaryDrawerItem item4 = new SecondaryDrawerItem().withIdentifier(3).withName(getResources().getString(R.string.nav_item_device_storage)).withIcon(FontAwesome.Icon.faw_archive);
        SecondaryDrawerItem item5 = new SecondaryDrawerItem().withIdentifier(4).withName(getResources().getString(R.string.nav_item_history)).withIcon(FontAwesome.Icon.faw_history);

        SecondaryDrawerItem item6 = new SecondaryDrawerItem().withIdentifier(5).withName(getResources().getString(R.string.nav_item_about)).withIcon(FontAwesome.Icon.faw_users);
        SecondaryDrawerItem item7 = new SecondaryDrawerItem().withIdentifier(6).withName(getResources().getString(R.string.nav_item_change_password)).withIcon(FontAwesome.Icon.faw_key);
        SecondaryDrawerItem item8 = new SecondaryDrawerItem().withIdentifier(7).withName(getResources().getString(R.string.nav_item_log_out)).withIcon(FontAwesome.Icon.faw_sign_out);

        //create the drawer and remember the `Drawer` result object
        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withActionBarDrawerToggle(true)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        item2,
                        item3,
                        item4,
                        item5,
                        new DividerDrawerItem(),
                        item6,
                        item7,
                        item8

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        switch ((int) drawerItem.getIdentifier()) {
                            case 1:
                                onAddTreeItemClick(drawerItem);
                                break;
                            case 2:
                                onGrowingTipsItemClick(drawerItem);
                                break;
                            case 3:
                                onStorageItemClick(drawerItem);
                                break;
                            case 4:
                                onHistoryItemClick(drawerItem);
                                break;
                            case 5:
                                onAboutItemClick(drawerItem);
                                break;
                            case 6:
                                onPasswordItemClick(drawerItem);
                                break;
                            case 7:
                                onLogoutItemClick();
                                break;
                        }
                        return true;
                    }
                })
                .build();
    }

    protected void onLogoutItemClick() {
    }

    protected void onPasswordItemClick(IDrawerItem drawerItem) {
    }

    protected void onAboutItemClick(IDrawerItem drawerItem) {
    }

    protected void onHistoryItemClick(IDrawerItem drawerItem) {
    }

    protected void onStorageItemClick(IDrawerItem drawerItem) {
    }

    protected void onGrowingTipsItemClick(IDrawerItem drawerItem) {
    }

    protected void onAddTreeItemClick(IDrawerItem drawerItem) {
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    protected void onHeaderContactClick() {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "0967913097", null));
        startActivity(intent);
    }

    protected void onHeaderSearchClick() {
    }

    protected void onHeaderHomeClick() {
    }


    @Override
    public void onBackPressed() {
        closeActivity();
    }

    private void bindView() {
        mToolbar = ButterKnife.findById(this, R.id.toolbar);
//        mDividerTop = ButterKnife.findById(this, R.id.divider_top);
        mMainContainer = ButterKnife.findById(this, R.id.main_container);
//        mMenu = ButterKnife.findById(this, R.id.im_menu);
        mHome = ButterKnife.findById(this, R.id.im_home);
        mLogo = ButterKnife.findById(this, R.id.im_logo);
        mSearch = ButterKnife.findById(this, R.id.im_search);
        mContact = ButterKnife.findById(this, R.id.im_contact);
    }

    protected void setMainContainer(@LayoutRes int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null, false);
        mMainContainer.addView(view);
    }

    protected void showToolbar(boolean isShow) {
        mToolbar.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    protected void closeActivity() {
        KeyboardUtils.hideSoftInput(this);
        finish();
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_up);
    }

    @OnClick({R.id.im_home, R.id.im_search, R.id.im_contact})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_home:
                onHeaderHomeClick();
                break;
            case R.id.im_search:
                onHeaderSearchClick();
                break;
            case R.id.im_contact:
                onHeaderContactClick();
                break;
        }
    }
}
