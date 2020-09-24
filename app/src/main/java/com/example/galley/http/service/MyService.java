package com.example.galley.http.service;



import com.example.galley.http.module.PixaPhoto;

import java.util.HashMap;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


//https://pixabay.com/api/?key=18406861-c6e0c4888076e3bb006248fd0&q=yellow+flowers&image_type=photo
public interface MyService {
    @GET("api/")
    Call<PixaPhoto> getPhoto(@QueryMap HashMap<String, String> params);
    //Call<PixaPhoto> getPhoto(@Url String params);


}
