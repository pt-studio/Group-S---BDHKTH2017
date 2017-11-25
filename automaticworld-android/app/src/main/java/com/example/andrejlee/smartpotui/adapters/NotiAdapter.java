package com.example.andrejlee.smartpotui.adapters;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.andrejlee.smartpotui.R;
import com.example.andrejlee.smartpotui.constants.Constants;
import com.example.andrejlee.smartpotui.entities.api.GrowTipEntity;
import com.example.andrejlee.smartpotui.entities.api.NotiEntity;

import java.util.List;

import butterknife.BindView;

public class NotiAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<NotiEntity> mListItem;
    private Context mContext;
    private int mLayoutId;
    private TextToSpeech tts;

    public NotiAdapter(Context context, int layoutId) {
        mContext = context;
        mLayoutId = layoutId;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = new NotiItemViewHolder(parent, mLayoutId);
        handleEventOnView(holder);
        return holder;
    }

    private void handleEventOnView(BaseViewHolder holder) {
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String text = mListItem.get(holder.getAdapterPosition()).getContent();
//                tts.setLanguage(Lo)
//                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
//            }
//        });
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        NotiEntity entity = mListItem.get(position);
        holder.onBind(entity);
    }

    @Override
    public int getItemCount() {
        return mListItem == null? 0 : mListItem.size();
    }

    public void setmListItem(List<NotiEntity> mListItem) {
        this.mListItem = mListItem;
        notifyDataSetChanged();
    }

    public class NotiItemViewHolder extends BaseViewHolder<NotiEntity> {

        @BindView(R.id.tv_noti_title)
        TextView mTitle;
        @BindView(R.id.tv_noti_content)
        TextView mContent;

        protected NotiItemViewHolder(ViewGroup parent, @LayoutRes int itemLayoutId) {
            super(parent, itemLayoutId);
        }

        @Override
        public void onBind(NotiEntity notiEntity) {
            mTitle.setText(notiEntity.getTreeName());
            mContent.setText(notiEntity.getContent());
        }
    }
}
