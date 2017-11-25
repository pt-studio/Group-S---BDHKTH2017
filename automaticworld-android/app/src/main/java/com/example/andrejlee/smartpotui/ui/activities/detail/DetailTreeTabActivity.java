package com.example.andrejlee.smartpotui.ui.activities.detail;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.constants.Constants;
import com.example.andrejlee.smartpotui.ui.activities.update_tree.UpdateTreeActivity;
import com.example.andrejlee.smartpotui.ui.bases.BaseDefaultActivity;
import com.example.andrejlee.smartpotui.ui.fragments.light.LightTabFragment;
import com.example.andrejlee.smartpotui.ui.fragments.moisture.MoistureTabFragment;
import com.example.andrejlee.smartpotui.ui.fragments.temperature.TempTabFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailTreeTabActivity extends BaseDefaultActivity implements DetailTreeView {

    @BindView(R.id.tv_screen_tree_name)
    TextView mScreenTitle;
    @BindView(R.id.tv_edit_tree)
    TextView mEdit;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    @BindView(R.id.container)
    ViewPager mViewPager;
    @BindView(R.id.tre_detail_tabs)
    TabLayout mTabLayout;

    //Layout
//    public static int[] resourceIds = {
//            R.layout.fragment_moisture
//            , R.layout.fragment_light
//            , R.layout.fragment_temp
//    };
    private DetailTreePresenter mPresenter;
    private static int mCurrentTreeId;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    // Tab titles
    private String[] tabs = {"Độ ẩm", "Ánh sáng", "Nhiệt độ"};

    public static void start(Activity context, int treeId) {
        Intent starter = new Intent(context, DetailTreeTabActivity.class);
        starter.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        starter.putExtra(Constants.STRING_TREE_ID_INTENT, treeId);
        context.startActivity(starter);
        context.overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContainer(R.layout.activity_detail_tree_tab);
        showToolbar(false);
        ButterKnife.bind(this);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.


        // Set up the ViewPager with the sections adapter.
//        mViewPager = (ViewPager) findViewById(R.id.container);

        mPresenter = new DetailTreePresenter();
        mPresenter.attachView(this);
        initViews();


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

    }

    private void initViews() {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        Intent intent = getIntent();

        if (intent != null) {
            mCurrentTreeId = intent.getIntExtra(Constants.STRING_TREE_ID_INTENT, Constants.NEGA_ONE_VALUE);
        }
        if (mCurrentTreeId != Constants.NEGA_ONE_VALUE) {
            mPresenter.getDeviceById(mCurrentTreeId);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_tree_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void setScreenTitle(String title) {
        if (title != null) {
            mScreenTitle.setText(title);
        }
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

//    public static class PlaceholderFragment extends Fragment {
//
//        private static final String ARG_SECTION_NUMBER = "section_number";
//
//        public PlaceholderFragment() {
//        }
//
//        public static PlaceholderFragment newInstance(int sectionNumber) {
//            PlaceholderFragment fragment = new PlaceholderFragment();
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
//            return fragment;
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
////            View rootView = inflater.inflate(R.layout.fragment_detail_tree_tab, container, false);
////            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
////            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
//            int index = getArguments().getInt(ARG_SECTION_NUMBER);
//            View rootView = inflater.inflate(resourceIds[index], container, false);
//            return rootView;
//        }
//    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
//            return PlaceholderFragment.newInstance(position + 1);
            switch (position) {
                case 0:
                    MoistureTabFragment tab1 = new MoistureTabFragment();
                    tab1.setCurrentTreeId(mCurrentTreeId);
                    return tab1;
                case 1:
                    LightTabFragment tab2 = new LightTabFragment();
                    tab2.setCurrentTreeId(mCurrentTreeId);
                    return tab2;
                case 2:
                    TempTabFragment tab3 = new TempTabFragment();
                    tab3.setCurrentTreeId(mCurrentTreeId);
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return tabs.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//                    return "SECTION 1";
//                case 1:
//                    return "SECTION 2";
//                case 2:
//                    return "SECTION 3";
//            }
//            return null;
            return tabs[position];
        }
    }

    @OnClick(R.id.tv_edit_tree)
    public void onClick(View v) {
        UpdateTreeActivity.start(this, mCurrentTreeId);
    }
}
