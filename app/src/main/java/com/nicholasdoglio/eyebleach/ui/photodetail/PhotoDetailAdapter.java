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
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nicholasdoglio.eyebleach.R;
import com.nicholasdoglio.eyebleach.data.model.reddit.ChildData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nicholas Doglio
 */
class PhotoDetailAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<ChildData> images = new ArrayList<>();

    //TODO: add a loading screen for images instead of just appearing, looking into Glide transitions

    PhotoDetailAdapter(Context context, List<ChildData> posts) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        images = posts;
    }

    @Override
    public int getCount() {
        return images.size();
    }


    public int loadMoreCallPosition() {
        return images.size() - 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = layoutInflater.inflate(R.layout.gallery_photo_detail, container, false);

        ImageView imageView = itemView.findViewById(R.id.gallery_photo);

        Glide.with(context)
                .load(images.get(position).getUrl())
                .into(imageView);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}