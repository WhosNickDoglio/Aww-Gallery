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

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.nicholasdoglio.eyebleach.data.model.reddit.ChildData;

import java.util.Objects;

/**
 * @author Nicholas Doglio
 */
public class PhotoStreamAdapter extends PagedListAdapter<ChildData, PhotoStreamAdapter.PhotoStreamViewHolder> {

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
    private Context photoStreamContext;

    protected PhotoStreamAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.photoStreamContext = context;
    }

    @Override
    public PhotoStreamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PhotoStreamViewHolder holder, int position) {

    }

    public class PhotoStreamViewHolder extends RecyclerView.ViewHolder {
        public PhotoStreamViewHolder(View itemView) {
            super(itemView);
        }
    }
}
