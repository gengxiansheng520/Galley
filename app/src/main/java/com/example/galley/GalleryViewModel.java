package com.example.galley;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.galley.http.HttpHelper;
import com.example.galley.http.module.Hit;
import com.example.galley.http.module.PixaPhoto;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryViewModel extends AndroidViewModel {

    private MutableLiveData<List<Hit>> _photosLive = new MutableLiveData<>();
    public LiveData<List<Hit>> photoLive;
    private HttpHelper httpHelper;
    private String[] qList = {"cat", "dog", "car", "beauty", "phone", "computer", "flower", "animal"};
    private HashMap<String, String> map = new HashMap<>();
    public GalleryViewModel(@NonNull Application application) {
        super(application);
        httpHelper = HttpHelper.getINSTANCE();
    }

    public LiveData<List<Hit>> getPhotoLive() {
        photoLive = _photosLive;
        return photoLive;
    }
    public void fetchData() {
        String key = "18406861-c6e0c4888076e3bb006248fd0";
        Random random = new Random();
        String q = qList[random.nextInt(qList.length-1)];
        String image_type = "photo";
        map.put("key", key);
        map.put("q", q);
        //map.put("image_type", image_type);
        map.put("per_page","100");
        map.put("pretty","true");
        httpHelper.getPhoto(map).enqueue(new Callback<PixaPhoto>() {
            @Override
            public void onResponse(Call<PixaPhoto> call, Response<PixaPhoto> response) {
                PixaPhoto pixaPhoto = response.body();
                if (pixaPhoto != null) {
                    List<Hit> hits = pixaPhoto.getHits();
                   _photosLive.setValue(hits);
                }
            }
            @Override
            public void onFailure(Call<PixaPhoto> call, Throwable t) {
//                if (binding.Refresh.isRefreshing()) {
//                    binding.Refresh.setRefreshing(false);
//                }
            }
        });
    }

}
