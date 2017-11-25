package com.example.andrejlee.smartpotui.ui.fragments.storage;

import com.example.andrejlee.smartpotui.ui.bases.MvpView;

public interface StorageFragmentView extends MvpView {
    void setAvailPotContent(String s);

    void setUnavailPotContent(String s);

    void setAvailAutoContent(String s);

    void setUnvailAutoContent(String s);

    void setSmartPB(int i);

    void setAutoPB(int i);
}
