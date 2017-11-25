package com.example.andrejlee.smartpotui.common;

import com.example.andrejlee.smartpotui.entities.api.GrowTipEntity;
import com.example.andrejlee.smartpotui.entities.api.NotiEntity;
import com.example.andrejlee.smartpotui.entities.api.TreeEntity;
import com.example.andrejlee.smartpotui.entities.api.TypeEntity;
import com.example.andrejlee.smartpotui.network.SmartPotNetworkManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class SmartPotFacade {
    private final Scheduler OBSERVER_SCHEDULER;
    private final Scheduler SUBSCRIBE_SCHEDULER;

    SmartPotFacade() {
        this.OBSERVER_SCHEDULER = AndroidSchedulers.mainThread();
        this.SUBSCRIBE_SCHEDULER = Schedulers.io();
    }

    public static SmartPotFacade getInstance() {
        return SmartPotSingle.INSTANCE;
    }

    public Observable<List<TreeEntity>> getAllDevices() {
        return SmartPotNetworkManager.getApiService().getAllDevices()
                .subscribeOn(SUBSCRIBE_SCHEDULER)
                .observeOn(OBSERVER_SCHEDULER);
    }

    public Observable<TreeEntity> getDeviceById(int id) {
        return SmartPotNetworkManager.getApiService().getDeviceById(id)
                .subscribeOn(SUBSCRIBE_SCHEDULER)
                .observeOn(OBSERVER_SCHEDULER);
    }

    public Observable<TreeEntity> updateTree(int id, String name, String username, int safeValue) {
        JSONObject paramObject = new JSONObject();
        JSONObject attributeObject = new JSONObject();
        try {
            attributeObject.put("safe_value", safeValue);
            paramObject.put("name", name);
            paramObject.put("username", username);
            paramObject.put("attribute", attributeObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json"), paramObject.toString());
        return SmartPotNetworkManager.getApiService().updateTree(id, body)
                .subscribeOn(SUBSCRIBE_SCHEDULER)
                .observeOn(OBSERVER_SCHEDULER);
    }

    public Observable<TreeEntity> addTree(String name, int deviceType, int safeValue) {
        JSONObject paramObject = new JSONObject();
        try {
            paramObject.put("name", name);
            paramObject.put("device_type", deviceType);
            paramObject.put("safe_value", safeValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json"), paramObject.toString());
        return SmartPotNetworkManager.getApiService().addTree(body)
                .subscribeOn(SUBSCRIBE_SCHEDULER)
                .observeOn(OBSERVER_SCHEDULER);
    }

    public Observable<List<GrowTipEntity>> getAllBlogs() {
        return SmartPotNetworkManager.getApiService().getAllBlogs()
                .subscribeOn(SUBSCRIBE_SCHEDULER)
                .observeOn(OBSERVER_SCHEDULER);
    }

    public Observable<GrowTipEntity> getBlogById(int id) {
        return SmartPotNetworkManager.getApiService().getBlogById(id)
                .subscribeOn(SUBSCRIBE_SCHEDULER)
                .observeOn(OBSERVER_SCHEDULER);
    }

    public Observable<List<NotiEntity>> getAllNotis() {
        return SmartPotNetworkManager.getApiService().getAllNotis()
                .subscribeOn(SUBSCRIBE_SCHEDULER)
                .observeOn(OBSERVER_SCHEDULER);
    }

    public Observable<List<TypeEntity>> getAllTypes() {
        return SmartPotNetworkManager.getApiService().getAllType()
                .subscribeOn(SUBSCRIBE_SCHEDULER)
                .observeOn(OBSERVER_SCHEDULER);
    }

    public Observable<TypeEntity> getTypeById(int id) {
        return SmartPotNetworkManager.getApiService().getTypeById(id)
                .subscribeOn(SUBSCRIBE_SCHEDULER)
                .observeOn(OBSERVER_SCHEDULER);
    }

    public static class SmartPotSingle {
        private static final SmartPotFacade INSTANCE = new SmartPotFacade();
    }
}
