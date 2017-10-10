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

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nicholasdoglio.eyebleach.R;
import com.nicholasdoglio.eyebleach.data.model.reddit.ChildData;
import com.nicholasdoglio.eyebleach.util.OnLoadMoreListener;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Nicholas Doglio
 */
public class PhotoDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private OnLoadMoreListener onLoadMoreListener;
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 3;
    private int firstVisibleItem;
    private int visibleItemCount;
    private int totalItemCount;
    private Context photoDetailContext;
    private List<ChildData> photoDetailList;
    private RecyclerView photoGridRecyclerView;

    //TODO: add a loading screen for images instead of just appearing, looking into Glide transitions
    PhotoDetailAdapter(Context photoDetailContext, List<ChildData> photoDetailList, RecyclerView photoGridRecyclerView) {
        this.photoDetailContext = photoDetailContext;
        this.photoDetailList = photoDetailList;
        this.photoGridRecyclerView = photoGridRecyclerView;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) photoGridRecyclerView.getLayoutManager();
        photoGridRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                    onLoadMoreListener.onLoadMore();
                    loading = true;
                }
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View regularView = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_gallery_item, parent, false);
        return new PhotoDetailAdapter.PhotoDetailViewHolder(regularView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChildData childData = photoDetailList.get(position);
        if (childData != null) {
            PhotoDetailViewHolder photoDetailViewHolder = (PhotoDetailViewHolder) holder;
            photoDetailViewHolder.bindTo(childData);
        }
    }

    @Override
    public int getItemCount() {
        return photoDetailList.size();
    }

    void addMore(List<ChildData> childData) {
        if (photoDetailList == null) {
            photoDetailList = childData;
            notifyItemRangeInserted(0, childData.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return photoDetailList.size();
                }

                @Override
                public int getNewListSize() {
                    return childData.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return Objects.equals(photoDetailList.get(oldItemPosition).getId(), childData.get(newItemPosition).getId());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    ChildData newPost = childData.get(newItemPosition);
                    ChildData oldPost = photoDetailList.get(oldItemPosition);
                    return Objects.equals(newPost.getId(), oldPost.getId())
                            && Objects.equals(newPost.getPermalink(), oldPost.getPermalink())
                            && Objects.equals(newPost.getUrl(), oldPost.getUrl())
                            && Objects.equals(newPost.getThumbnail(), oldPost.getThumbnail());
                }
            });
            photoDetailList = childData;
            result.dispatchUpdatesTo(this);
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
            Glide.with(photoDetailContext)
                    .load(childData.getUrl())
                    .into(photoDetailImageView);
        }
    }
}