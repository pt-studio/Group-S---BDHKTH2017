package com.example.andrejlee.smartpotui.entities.api;

import com.example.andrejlee.smartpotui.constants.Constants;
import com.google.gson.annotations.SerializedName;

public class TreeEntity {

    @SerializedName("id")
    private int mId;
    @SerializedName("device_type")
    private int mDeviceType;
    @SerializedName("user")
    private int mUser;
    @SerializedName("code_name")
    private String mCodeName;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("attribute")
    private AttributeEnity mAttribute;
    @SerializedName("name")
    private String mName;

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public int getDeviceType() {
        return mDeviceType;
    }

    public void setDeviceType(int mDeviceType) {
        this.mDeviceType = mDeviceType;
    }

    public int getUser() {
        return mUser;
    }

    public void setUser(int mUser) {
        this.mUser = mUser;
    }

    public String getCodeName() {
        return mCodeName;
    }

    public void setCodeName(String mCodeName) {
        this.mCodeName = mCodeName;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public AttributeEnity getAttribute() {
        return mAttribute;
    }

    public void setAttribute(AttributeEnity mAttribute) {
        this.mAttribute = mAttribute;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getImageStatus() {
        int temp = mAttribute.getSaveValue() - mAttribute.getHumidity();

        if (mStatus != null) {
            if (mStatus.equals(Constants.STATUS_OFF)) {
                return Constants.IMAGE_STATUS_ANGRY;
            } else {
                if (temp >= 0) {
                    return Constants.IMAGE_STATUS_SMILE;
                } else {
                    return Constants.IMAGE_STATUS_CRY;
                }
            }
        }
        return 0;
    }
}
