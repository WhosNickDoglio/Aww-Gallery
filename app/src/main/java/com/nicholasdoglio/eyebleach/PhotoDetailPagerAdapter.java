package com.nicholasdoglio.eyebleach;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nicholasdoglio.eyebleach.model.reddit.Child;

import java.util.List;

public class PhotoDetailPagerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Child> images;


    public PhotoDetailPagerAdapter(Context context, List<Child> images) {
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
        View itemView = layoutInflater.inflate(R.layout.gallery_photo_detail, container, false);

        String postUrl = "https://reddit.com" + images.get(position).getChildData().getPermalink();

        ImageButton openInBrowserButton = itemView.findViewById(R.id.open_in_browser);
        ImageButton shareButton = itemView.findViewById(R.id.share_source);

        ImageView imageView = itemView.findViewById(R.id.gallery_photo);

        Glide.with(context).load(images.get(position).getChildData().getUrl())
                .into(imageView);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}