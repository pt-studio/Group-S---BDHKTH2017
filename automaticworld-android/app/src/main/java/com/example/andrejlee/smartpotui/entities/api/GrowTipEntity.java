package com.example.andrejlee.smartpotui.entities.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrej Lee on 11/17/2017.
 */

public class GrowTipEntity {

    @SerializedName("id")
    private int mId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("image")
    private String mImage;
    @SerializedName("content")
    private String mContent;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String mImage) {
        this.mImage = mImage;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }
}
