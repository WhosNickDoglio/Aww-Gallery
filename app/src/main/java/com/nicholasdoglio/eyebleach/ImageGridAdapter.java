package com.nicholasdoglio.eyebleach;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nicholasdoglio.eyebleach.model.Child;

import java.util.ArrayList;
import java.util.List;

public class ImageGridAdapter extends RecyclerView.Adapter<ImageGridAdapter.ViewHolder> {
    private Context context;
    private List<Child> data = new ArrayList<>();

    public ImageGridAdapter(Context context, List<Child> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_item_grid, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(data.get(position).getData().getThumbnail())
                .placeholder(R.mipmap.photo_placeholder)
                .fitCenter()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_grid);
        }
    }
}

