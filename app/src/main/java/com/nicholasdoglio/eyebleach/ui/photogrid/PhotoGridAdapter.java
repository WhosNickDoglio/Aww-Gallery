/*
    Aww Gallery
    Copyright (C) 2017  Nicholas Doglio

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
import com.nicholasdoglio.eyebleach.data.model.reddit.ChildData;
import com.nicholasdoglio.eyebleach.util.Intents;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Nicholas Doglio
 */
public class PhotoGridAdapter extends PagedListAdapter<ChildData, PhotoGridAdapter.ViewHolder> {
    private static final DiffCallback<ChildData> DIFF_CALLBACK = new DiffCallback<ChildData>() {
        @Override
        public boolean areItemsTheSame(@NonNull ChildData oldItem, @NonNull ChildData newItem) {
            return Objects.equals(oldItem.getId(), newItem.getId());
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

        String finalId = id;
        holder.imageView.setOnClickListener(view -> Intents.startDetailActivity(view, finalId));
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