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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nicholasdoglio.eyebleach.R;
import com.nicholasdoglio.eyebleach.data.model.reddit.ChildData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Nicholas Doglio
 */
public class PhotoStreamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context photoStreamContext;
    private List<ChildData> photoStreamList;

    public PhotoStreamAdapter(Context photoStreamContext, List<ChildData> photoStreamList) {
        this.photoStreamContext = photoStreamContext;
        this.photoStreamList = photoStreamList;
    }

    @Override
    public PhotoStreamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View regularView = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_stream_item, parent, false);
        return new PhotoStreamAdapter.PhotoStreamViewHolder(regularView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChildData childData = photoStreamList.get(position);
        if (childData != null) {
            PhotoStreamViewHolder photoStreamViewHolder = (PhotoStreamViewHolder) holder;
            photoStreamViewHolder.bindTo(childData);
        }

    }

    @Override
    public int getItemCount() {
        return photoStreamList.size();
    }

    public class PhotoStreamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.image_stream)
        ImageView photoStreamFullImageView;

        PhotoStreamViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindTo(ChildData childData) {
            RequestOptions options = new RequestOptions()
                    .error(R.drawable.cat_error);

            Glide.with(photoStreamContext)
                    .load(childData.getUrl())
                    .apply(options)
                    .into(photoStreamFullImageView);
        }

        @Override
        public void onClick(View view) {
            // TODO: Create a dialog for Share and open source
        }
    }
}