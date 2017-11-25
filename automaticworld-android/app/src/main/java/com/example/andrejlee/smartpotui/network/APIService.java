package com.example.andrejlee.smartpotui.network;


import com.example.andrejlee.smartpotui.entities.api.GrowTipEntity;
import com.example.andrejlee.smartpotui.entities.api.NotiEntity;
import com.example.andrejlee.smartpotui.entities.api.TreeEntity;
import com.example.andrejlee.smartpotui.entities.api.TypeEntity;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {
////    TODO serviceAPI NO.14
//    @POST("member/check_mail")
//    Observable<BaseResultEntity> checkEmailExisted(@Body RequestBody body);

    //    TODO get all device API
    @GET("api/v1/user_devices/")
    Observable<List<TreeEntity>> getAllDevices();

    //    TODO get a device by Id
    @GET("api/v1/user_devices/{id}/")
    Observable<TreeEntity> getDeviceById(@Path("id") int id);

    //    TODO update a device
    @PUT("api/v1/user_devices/mobile_update/{id}/")
    Observable<TreeEntity> updateTree(@Path("id") int id, @Body RequestBody body);

    //    TODO add a device
    @PUT("api/v1/user_devices/add_tree/")
    Observable<TreeEntity> addTree(@Body RequestBody body);

    //    TODO get all blogs
    @GET("api/v1/blogs/")
    Observable<List<GrowTipEntity>> getAllBlogs();

    //    TODO get blog by id
    @GET("api/v1/blogs/{id}/")
    Observable<GrowTipEntity> getBlogById(@Path("id") int id);

    //    TODO get all types
    @GET("api/v1/devices/")
    Observable<List<TypeEntity>> getAllType();

    //    TODO get type by id
    @GET("api/v1/devices/{id}/")
    Observable<TypeEntity> getTypeById(@Path("id") int id);

    //    TODO get all notis
    @GET("api/v1/notifications/")
    Observable<List<NotiEntity>> getAllNotis();
}
