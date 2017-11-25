package com.example.andrejlee.smartpotui.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GrowTipsAdapter extends RecyclerView.Adapter<BaseViewHolder> implements Filterable {

    private List<GrowTipEntity> mListItem;
    private Context mContext;
    private int mLayoutId;
    private List<GrowTipEntity> mOriginalValues;

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
        return mListItem != null ? mListItem.size() : 0;
    }

    public void setmListItem(List<GrowTipEntity> mListItem) {
        this.mListItem = mListItem;
        this.mOriginalValues = mListItem;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mListItem = (ArrayList<GrowTipEntity>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<GrowTipEntity> FilteredArrList = new ArrayList<>();

                if (mOriginalValues == null) {
                    mOriginalValues = new ArrayList<>(mListItem); // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < mOriginalValues.size(); i++) {
                        String data = mOriginalValues.get(i).getTitle();
                        if (data.toLowerCase().contains(constraint.toString())) {
                            FilteredArrList.add(mOriginalValues.get(i));
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
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

            mImage.setImageResource(growTipEntity.getImageId());

//            if (growTipEntity.getImage() != null && !growTipEntity.getImage().equals(Constants.EMPTY_STRING)) {
//                Glide.with(mContext)
//                        .load(growTipEntity.getImage())
//                        .fitCenter()
//                        .dontAnimate()
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .into(mImage);
//            } else {
//                mImage.setImageResource(growTipEntity.getImageId());
//            }
        }
    }
}
