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
package com.nicholasdoglio.eyebleach.ui.photolist;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nicholasdoglio.eyebleach.R;
import com.nicholasdoglio.eyebleach.data.model.reddit.ChildData;
import com.nicholasdoglio.eyebleach.util.Intents;
import com.nicholasdoglio.eyebleach.util.OnLoadMoreListener;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Nicholas Doglio
 */
public class PhotoGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // TODO: Support Glide-RecyclerView
    // TODO: Need to add loading footer
    public static final int VIEW_TYPE_ITEM = 0;
    public static final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener onLoadMoreListener;
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 3;
    private int firstVisibleItem;
    private int visibleItemCount;
    private int totalItemCount;
    private Context photoGridContext;
    private List<ChildData> photoGridList;
    private RecyclerView photoGridRecyclerView;


    PhotoGridAdapter(Context photoGridContext, List<ChildData> photoGridList, RecyclerView photoGridRecyclerView) {
        this.photoGridContext = photoGridContext;
        this.photoGridList = photoGridList;
        this.photoGridRecyclerView = photoGridRecyclerView;

        final GridLayoutManager gridLayoutManager = (GridLayoutManager) this.photoGridRecyclerView.getLayoutManager();
        this.photoGridRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = gridLayoutManager.getItemCount();
                firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    loading = true;
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return photoGridList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_grid_item, parent, false);
            return new PhotoGridViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_list_loading_item, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PhotoGridViewHolder) {
            ChildData childData = photoGridList.get(position);
            PhotoGridViewHolder userViewHolder = (PhotoGridViewHolder) holder;
            userViewHolder.bindTo(childData);
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return photoGridList == null ? 0 : photoGridList.size();
    }

    void setLoaded() {
        loading = false;
    }

    void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    void swipeRefresh() {
        previousTotal = 0;
        firstVisibleItem = 0;
        visibleItemCount = 0;
        totalItemCount = 0;
    }

    void addMore(List<ChildData> childData) {
        if (photoGridList == null) {
            photoGridList = childData;
            notifyItemRangeInserted(0, childData.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return photoGridList.size();
                }

                @Override
                public int getNewListSize() {
                    return childData.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return Objects.equals(photoGridList.get(oldItemPosition).getId(), childData.get(newItemPosition).getId());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    ChildData newPost = childData.get(newItemPosition);
                    ChildData oldPost = photoGridList.get(oldItemPosition);
                    return Objects.equals(newPost.getId(), oldPost.getId())
                            && Objects.equals(newPost.getPermalink(), oldPost.getPermalink())
                            && Objects.equals(newPost.getUrl(), oldPost.getUrl())
                            && Objects.equals(newPost.getThumbnail(), oldPost.getThumbnail());
                }
            });
            photoGridList = childData;
            result.dispatchUpdatesTo(this);
        }
    }

    public class PhotoGridViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.image_grid)
        ImageView photoGridThumbnailImageView;

        PhotoGridViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        void bindTo(ChildData childData) {
            RequestOptions options = new RequestOptions()
                    .error(R.drawable.cat_error);

            Glide.with(photoGridContext)
                    .load(childData.getThumbnail())
                    .apply(options)
                    .into(photoGridThumbnailImageView);
        }

        @Override
        public void onClick(View view) {
            Intents.startDetailActivity(view, getAdapterPosition());
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.photo_list_loading_bar)
        ProgressBar progressBar;

        LoadingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}