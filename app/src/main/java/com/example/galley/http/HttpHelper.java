package com.example.galley.http;



import android.util.Log;

import com.example.galley.http.module.PixaPhoto;
import com.example.galley.http.service.MyService;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpHelper {
    private static HttpHelper INSTANCE;
    private Retrofit retrofit;
    private MyService myService;

    public synchronized static HttpHelper getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new HttpHelper();
        }
        return INSTANCE;
    }
    //https://pixabay.com/api/?key=18406861-c6e0c4888076e3bb006248fd0&q=yellow+flowers&image_type=photo
    private HttpHelper() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myService = retrofit.create(MyService.class);
    }

    public Call<PixaPhoto> getPhoto(HashMap<String, String> map) {
        String url = "https://pixabay.com/api/?key=18406861-c6e0c4888076e3bb006248fd0&q=yellow+flowers&image_type=photo";
        return myService.getPhoto(map);
    }

}
