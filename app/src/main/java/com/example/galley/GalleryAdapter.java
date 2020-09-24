package com.example.galley;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.galley.http.module.Hit;

import java.io.File;

import io.supercharge.shimmerlayout.ShimmerLayout;

public class GalleryAdapter extends ListAdapter<Hit, GalleryAdapter.GalleryViewHolder> {
    private OnItemHelper onItemHelper;

    public void setOnItemHelper(OnItemHelper onItemHelper) {
        this.onItemHelper = onItemHelper;
    }

    public GalleryAdapter() {
        super(new DiffUtil.ItemCallback<Hit>() {
            @Override
            public boolean areItemsTheSame(@NonNull Hit oldItem, @NonNull Hit newItem) {
                return false;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Hit oldItem, @NonNull Hit newItem) {
                return false;
            }
        });
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_cell,parent,false);
        GalleryViewHolder holder = new GalleryViewHolder(view);
        holder.imageView.setOnClickListener(view1->{
            int position = holder.getAdapterPosition();
            if (onItemHelper != null) {
                Hit hit = getItem(position);
                onItemHelper.click(hit);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        Hit hit = getItem(position);
        holder.shimmerLayout.setShimmerColor(0x55FFFFFF);
        holder.shimmerLayout.setShimmerAngle(0);
        holder.shimmerLayout.startShimmerAnimation();
        Glide.with(holder.itemView)
                //.load(hit.getPreviewURL())
                .load(hit.getLargeImageURL())
                .placeholder(R.drawable.ic_gray_photo_24)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.shimmerLayout.stopShimmerAnimation();
                        return false;
                    }
                })
                .into(holder.imageView);
    }
    class GalleryViewHolder extends RecyclerView.ViewHolder {
        ShimmerLayout shimmerLayout;
        ImageView imageView;

        public GalleryViewHolder(@NonNull View itemView) {
            super(itemView);
            shimmerLayout = itemView.findViewById(R.id.shimmerGalleryLayout);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }
    public interface OnItemHelper{
        void click(Hit hit);
    }


}
