package com.example.andrejlee.smartpotui.entities.api;

import com.google.gson.annotations.SerializedName;

public class TypeEntity {

    @SerializedName("name")
    private String mName;
    @SerializedName("code_name")
    private String mCodeName;
    @SerializedName("description")
    private String mDescription;

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getCodeName() {
        return mCodeName;
    }

    public void setCodeName(String mCodeName) {
        this.mCodeName = mCodeName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
