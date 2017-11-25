package com.example.andrejlee.smartpotui.ui.activities.growing_tips_detail;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.constants.Constants;
import com.example.andrejlee.smartpotui.ui.bases.BaseDefaultActivity;
import com.marshalchen.ultimaterecyclerview.ui.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GrowingTipsDetailActivity extends BaseDefaultActivity implements GrowingTipsDetailView {

    @BindView(R.id.iv_tree_detail)
    ImageView mTreeImage;
    @BindView(R.id.tv_detail_tree_name)
    TextView mTreeName;
    @BindView(R.id.tv_detail_tree_description)
    TextView mDescription;
    @BindView(R.id.fbtn_share)
    FloatingActionButton shareFloatBtn;
    @BindView(R.id.iv_back)
    ImageView mBackBtn;

    private GrowingTipsDetailPresenter mPresenter;
    private int mCurrentBlogId;


    public static void start(Activity context, int blogId) {
        Intent starter = new Intent(context, GrowingTipsDetailActivity.class);
        starter.putExtra(Constants.STRING_BLOG_ID_INTENT, blogId);
        starter.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(starter);
        context.overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContainer(R.layout.activity_growing_tips_detail);
        ButterKnife.bind(this);
        showToolbar(false);
        mPresenter = new GrowingTipsDetailPresenter();
        mPresenter.attachView(this);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    private void initViews() {
        Intent intent = getIntent();
        mCurrentBlogId = intent.getIntExtra(Constants.STRING_BLOG_ID_INTENT, Constants.NEGA_ONE_VALUE);
        mDescription.setMovementMethod(new ScrollingMovementMethod());
        mPresenter.getBlogById(mCurrentBlogId);
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
    public void setImageContent(int image) {
        mTreeImage.setImageResource(image);
//        Glide.with(this)
//                .load(image)
//                .fitCenter()
//                .dontAnimate()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(mTreeImage);
    }

    @Override
    public void setTitleContent(String title) {
        mTreeName.setText(title);
    }

    @Override
    public void setDescriptionContent(String content) {
        mDescription.setText(content);
    }

    @OnClick(R.id.iv_back)
    public void onClick(View v){
        closeActivity();
    }
}
