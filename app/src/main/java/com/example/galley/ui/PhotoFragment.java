package com.example.galley.ui;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.galley.R;
import com.example.galley.databinding.FragmentPhotoBinding;
import com.example.galley.http.module.Hit;

public class PhotoFragment extends Fragment {

    private FragmentPhotoBinding binding;

    public PhotoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        binding = FragmentPhotoBinding.bind(view);
        NavController controller = Navigation.findNavController(requireActivity(),R.id.fragment);
        Hit hit = getArguments().getParcelable("Hit");
        binding.shimmer.setShimmerColor(0x55FFFFFF);
        binding.shimmer.setShimmerAngle(0);
        binding.shimmer.startShimmerAnimation();
        Glide.with(requireActivity())
                .load(hit.getLargeImageURL())
                .placeholder(R.drawable.ic_gray_photo_24)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        binding.shimmer.stopShimmerAnimation();
                        return false;
                    }

                })
                .into(binding.photoView);
        return view;



    }
}