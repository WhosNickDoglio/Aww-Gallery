package com.nicholasdoglio.eyebleach;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.nicholasdoglio.eyebleach.model.Child;

import java.io.Serializable;
import java.util.List;

public class GalleryPagerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Child> images;

    public GalleryPagerAdapter(Context context, List<Child> images) {
        this.context = context;
        this.images = images;
        layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = layoutInflater.inflate(R.layout.gallery_item, container, false);

        ImageView imageView = itemView.findViewById(R.id.gallery_photo);


        if (images.get(position).getData().getUrl().contains(".gif")) {

            GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageView);

            Glide.with(context).load(images.get(position).getData().getUrl())
                    .placeholder(R.mipmap.photo_placeholder)
                    .fitCenter()
                    .into(imageViewTarget);

        } else {
            Glide.with(context).load(images.get(position).getData().getUrl())
                    .placeholder(R.mipmap.photo_placeholder)
                    .fitCenter()
                    .into(imageView);

        }

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}