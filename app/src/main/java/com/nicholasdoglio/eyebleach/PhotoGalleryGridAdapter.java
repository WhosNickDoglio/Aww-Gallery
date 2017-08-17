package com.nicholasdoglio.eyebleach;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nicholasdoglio.eyebleach.model.reddit.Child;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PhotoGalleryGridAdapter extends RecyclerView.Adapter<PhotoGalleryGridAdapter.ViewHolder> {
    private Context context;
    private List<Child> images = new ArrayList<>();

    public PhotoGalleryGridAdapter(Context context, List<Child> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_grid_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RequestOptions options = new RequestOptions()
                .error(R.drawable.cat_crying);


        Glide.with(context).load(images.get(position).getChildData().getThumbnail())
                .apply(options)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public void clear() {
        images.clear();
        notifyDataSetChanged();
    }

    public void add(Child mc) {
        images.add(mc);
        notifyItemInserted(images.size() - 1);
    }

    public void addAll(List<Child> children) {
        for (Child child : children) {
            add(child);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_grid)
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}