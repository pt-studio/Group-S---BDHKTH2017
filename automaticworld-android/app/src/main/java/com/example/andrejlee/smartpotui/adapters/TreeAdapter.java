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
import com.example.andrejlee.smartpotui.entities.api.TreeEntity;
import com.example.andrejlee.smartpotui.ui.activities.detail.DetailTreeActivity;
import com.example.andrejlee.smartpotui.ui.activities.detail.DetailTreeTabActivity;
import com.example.andrejlee.smartpotui.ui.activities.detail.DetailTreeView;

import java.util.List;

import butterknife.BindView;

public class TreeAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<TreeEntity> mListItem;
    private Context mContext;
    private int mLayoutId;

    public TreeAdapter(Context context, int layoutId) {
        mContext = context;
        mLayoutId = layoutId;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = new ItemViewHolder(parent, mLayoutId);
        handleEventOnView(holder);
        return holder;
    }

    private void handleEventOnView(BaseViewHolder holder) {
        holder.itemView.setOnClickListener(v -> {
//            DetailTreeActivity.start((Activity) mContext, mListItem.get(holder.getAdapterPosition()).getId());
            DetailTreeTabActivity.start((Activity) mContext, mListItem.get(holder.getAdapterPosition()).getId());
        });
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        TreeEntity entity = mListItem.get(position);
        holder.onBind(entity);
    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }

    public void setmListItem(List<TreeEntity> mListItem) {
        this.mListItem = mListItem;
        notifyDataSetChanged();
    }


    public class ItemViewHolder extends BaseViewHolder<TreeEntity> {

        @BindView(R.id.im_grid_item)
        ImageView mImage;
        @BindView(R.id.tv_grid_item)
        TextView mTitle;

        protected ItemViewHolder(ViewGroup parent, @LayoutRes int itemLayoutId) {
            super(parent, itemLayoutId);
        }

        @Override
        public void onBind(TreeEntity treeEntity) {
            mTitle.setText(treeEntity.getName());
            AttributeEnity tempAttribute = treeEntity.getAttribute();
            int tempId;
            if (tempAttribute != null) {
                switch (treeEntity.getImageStatus()) {
                    case Constants.IMAGE_STATUS_SMILE:
                        tempId = R.drawable.smile;
                        break;
                    case Constants.IMAGE_STATUS_CRY:
                        tempId = R.drawable.cry;
                        break;
                    case Constants.IMAGE_STATUS_ANGRY:
                        tempId = R.drawable.lose_connection;
                        break;
                    default:
                        tempId = R.drawable.smile;
                        break;
                }
                Glide.with(mContext)
                        .load(tempId)
                        .fitCenter()
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(mImage);
            }
        }
    }
}