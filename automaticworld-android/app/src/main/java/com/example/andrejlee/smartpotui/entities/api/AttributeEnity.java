package com.example.andrejlee.smartpotui.entities.api;

import com.google.gson.annotations.SerializedName;

public class AttributeEnity {

    @SerializedName("safe_value")
    private int mSaveValue;
    @SerializedName("humidity_value")
    private int mHumidity;

    public int getSaveValue() {
        return mSaveValue;
    }

    public void setSaveValue(int mSaveValue) {
        this.mSaveValue = mSaveValue;
    }

    public int getHumidity() {
        return mHumidity;
    }

    public void setHumidity(int mHumidity) {
        this.mHumidity = mHumidity;
    }
}
