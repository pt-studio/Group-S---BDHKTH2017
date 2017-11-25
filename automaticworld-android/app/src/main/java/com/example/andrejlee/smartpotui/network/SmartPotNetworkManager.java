package com.example.andrejlee.smartpotui.network;

import com.example.andrejlee.smartpotui.constants.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SmartPotNetworkManager {

    private static String sBaseUrlApi = "https://automaticworld.herokuapp.com/";
    private static String sToken = "Token 795c04d7b892240d364e475ad6ee88bef9f2d31e";
    private static APIService sApiService;

    //    PUBLIC METHODS
    public static APIService getApiService() {
        if (sApiService == null) {
            throw new NullPointerException("Please init SmartPotManager in Application");
        }
        return sApiService;
    }

    public static synchronized void init() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.interceptors().add(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Authorization", sToken)
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        });
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        OkHttpClient client = httpClient
                .addInterceptor(logging)
                .connectTimeout(Constants.CONNECTION_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.CONNECTION_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(Constants.READ_TIME_OUT, TimeUnit.SECONDS).build();

        String baseUrl = sBaseUrlApi;
        if (!baseUrl.startsWith("https")) {
            baseUrl = "https://" + baseUrl;
        }

        Gson gson = new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        sApiService = retrofit.create(APIService.class);
    }

}
