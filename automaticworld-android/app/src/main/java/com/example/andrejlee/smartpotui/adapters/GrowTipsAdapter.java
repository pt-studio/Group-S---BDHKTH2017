package com.example.andrejlee.smartpotui.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.constants.Constants;
import com.example.andrejlee.smartpotui.entities.api.AttributeEnity;
import com.example.andrejlee.smartpotui.entities.api.GrowTipEntity;
import com.example.andrejlee.smartpotui.entities.api.TreeEntity;
import com.example.andrejlee.smartpotui.ui.activities.growing_tips_detail.GrowingTipsDetailActivity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Andrej Lee on 11/18/2017.
 */

public class GrowTipsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<GrowTipEntity> mListItem;
    private Context mContext;
    private int mLayoutId;

    public GrowTipsAdapter(Context context, int layoutId) {
        mContext = context;
        mLayoutId = layoutId;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = new TipItemViewHolder(parent, mLayoutId);
        handleEventOnView(holder);
        return holder;
    }

    private void handleEventOnView(BaseViewHolder holder) {
        holder.itemView.setOnClickListener(v -> {
            GrowingTipsDetailActivity.start((Activity) mContext, mListItem.get(holder.getAdapterPosition()).getId());
        });
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        GrowTipEntity entity = mListItem.get(position);
        holder.onBind(entity);
    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }

    public void setmListItem(List<GrowTipEntity> mListItem) {
        this.mListItem = mListItem;
        notifyDataSetChanged();
    }

    public class TipItemViewHolder extends BaseViewHolder<GrowTipEntity> {

        @BindView(R.id.im_tree_image)
        ImageView mImage;
        @BindView(R.id.tv_grow_tip_name)
        TextView mTitle;
        @BindView(R.id.tv_description)
        TextView mDescription;

        protected TipItemViewHolder(ViewGroup parent, @LayoutRes int itemLayoutId) {
            super(parent, itemLayoutId);
        }

        @Override
        public void onBind(GrowTipEntity growTipEntity) {
            mTitle.setText(growTipEntity.getTitle());
            mDescription.setText(growTipEntity.getContent());

            if (growTipEntity.getImage() != null && !growTipEntity.getImage().equals(Constants.EMPTY_STRING)) {
                Glide.with(mContext)
                        .load(growTipEntity.getImage())
                        .fitCenter()
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(mImage);
            } else {
                mImage.setImageResource(R.mipmap.ic_launcher);
            }


        }
    }
}
