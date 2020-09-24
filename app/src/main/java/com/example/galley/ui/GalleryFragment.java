package com.example.galley.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;

import com.example.galley.GalleryAdapter;
import com.example.galley.GalleryViewModel;
import com.example.galley.MainActivity;
import com.example.galley.R;
import com.example.galley.databinding.FragmentGalleryBinding;
import com.example.galley.http.module.Hit;

import java.util.List;

public class GalleryFragment extends Fragment {
    private FragmentGalleryBinding binding;
    private GalleryAdapter galleryAdapter;
    private GalleryViewModel galleryViewModel;

    public GalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        binding = FragmentGalleryBinding.bind(view);
        galleryAdapter = new GalleryAdapter();
        galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(requireActivity(),2));
        binding.recyclerView.setAdapter(galleryAdapter);
        if (galleryViewModel.photoLive == null) {
            galleryViewModel.fetchData();
        }
        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                galleryViewModel.fetchData();
            }
        });
        galleryAdapter.setOnItemHelper(new GalleryAdapter.OnItemHelper() {
            @Override
            public void click(Hit hit) {
                NavController controller = Navigation.findNavController(requireActivity(),R.id.fragment);
                Bundle bundle = new Bundle();
                bundle.putParcelable("Hit", hit);
                controller.navigate(R.id.action_galleryFragment_to_photoFragment,bundle);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        galleryViewModel.getPhotoLive().observe(requireActivity(), new Observer<List<Hit>>() {
            @Override
            public void onChanged(List<Hit> hits) {
                if (!hits.isEmpty()) {
                    galleryAdapter.submitList(hits);
                } else {
                    galleryViewModel.fetchData();
                }
                if (binding.swipe.isRefreshing()) {
                    binding.swipe.setRefreshing(false);
                }
            }
        });
    }
}