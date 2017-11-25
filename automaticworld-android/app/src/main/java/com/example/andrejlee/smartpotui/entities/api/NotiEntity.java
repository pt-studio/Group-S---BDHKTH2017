package com.example.andrejlee.smartpotui.entities.api;

import com.google.gson.annotations.SerializedName;

public class NotiEntity {

    @SerializedName("content")
    private String mContent;
    @SerializedName("user_device")
    private int mUserDevice;
    private String mTreeName;

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

    public String getTreeName() {
        return mTreeName;
    }

    public void setTreeName(String mTreeName) {
        this.mTreeName = mTreeName;
    }
}
