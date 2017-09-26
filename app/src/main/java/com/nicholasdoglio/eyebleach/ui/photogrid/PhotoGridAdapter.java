package com.nicholasdoglio.eyebleach.ui.photogrid;


import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nicholasdoglio.eyebleach.R;
import com.nicholasdoglio.eyebleach.data.model.ChildData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoGridAdapter extends PagedListAdapter<ChildData, PhotoGridAdapter.ViewHolder> {
    private static final DiffCallback<ChildData> DIFF_CALLBACK = new DiffCallback<ChildData>() {
        @Override
        public boolean areItemsTheSame(@NonNull ChildData oldItem, @NonNull ChildData newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ChildData oldItem, @NonNull ChildData newItem) {
            return oldItem.equals(newItem);
        }
    };
    private Context context;

    PhotoGridAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View regularView = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_grid_item, parent, false);
        return new ViewHolder(regularView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChildData childData = getItem(position);
        String id = "";

        if (childData != null) {
            holder.bindTo(childData);
            id = childData.getId();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_grid)
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindTo(ChildData childData) {
            RequestOptions options = new RequestOptions()
                    .error(R.drawable.cat_error);

            Glide.with(context).load(childData.getThumbnail())
                    .apply(options)
                    .into(imageView);
        }
    }
}