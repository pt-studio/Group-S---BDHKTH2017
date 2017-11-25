package com.example.andrejlee.smartpotui.entities.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrej Lee on 11/17/2017.
 */

public class NotiEntity {

    @SerializedName("content")
    private String mContent;
    @SerializedName("user_device")
    private int mUserDevice;

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public int getUserDevice() {
        return mUserDevice;
    }

    public void setUserDevice(int mUserDevice) {
        this.mUserDevice = mUserDevice;
    }
}
