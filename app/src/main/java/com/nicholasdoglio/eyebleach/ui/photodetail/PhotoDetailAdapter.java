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
package com.nicholasdoglio.eyebleach.ui.photodetail;


import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nicholasdoglio.eyebleach.R;
import com.nicholasdoglio.eyebleach.data.model.reddit.ChildData;
import com.nicholasdoglio.eyebleach.util.GlideApp;
import com.nicholasdoglio.eyebleach.util.OnLoadMoreListener;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * @author Nicholas Doglio
 */
public class PhotoDetailAdapter extends PagedListAdapter<ChildData, PhotoDetailAdapter.PhotoDetailViewHolder> {
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
    //TODO: add a loading screen for images instead of just appearing, looking into Glide transitions
    private Context photoDetailContext;
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 3;
    private int firstVisibleItem;
    private int visibleItemCount;
    private int totalItemCount;
    private OnLoadMoreListener onLoadMoreListener;

    PhotoDetailAdapter(Context photoDetailContext, RecyclerView photoDetailRecyclerView) {
        super(DIFF_CALLBACK);
        this.photoDetailContext = photoDetailContext;
        final LinearLayoutManager layoutManager = (LinearLayoutManager) photoDetailRecyclerView.getLayoutManager();
        photoDetailRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount) - 12 <= (firstVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    loading = true;
                }
            }
        });
    }

    @Override
    public PhotoDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View regularView = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_gallery_item, parent, false);
        return new PhotoDetailViewHolder(regularView);
    }

    @Override
    public void onBindViewHolder(PhotoDetailViewHolder holder, int position) {
        ChildData childData = getItem(position);
        if (childData != null) {
            holder.bindTo(childData);
        }
    }

    void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    class PhotoDetailViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.gallery_photo)
        ImageView photoDetailImageView;

        PhotoDetailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindTo(ChildData childData) {
            GlideApp.with(photoDetailContext)
                    .load(childData.getUrl())
                    .transition(withCrossFade())
                    .into(photoDetailImageView);
        }
    }
}
