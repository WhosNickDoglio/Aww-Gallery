package com.nicholasdoglio.eyebleach.ui.photodetail;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nicholasdoglio.eyebleach.R;
import com.nicholasdoglio.eyebleach.data.model.ChildData;

import java.util.List;

class PhotoDetailAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<ChildData> images;


    //TODO: add a loading screen for images instead of just appearing, looking into Glide transitions

    PhotoDetailAdapter(Context context, List<ChildData> images) {
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

        ImageView imageView = itemView.findViewById(R.id.gallery_photo);


        if (images.get(position).getUrl().contains("gif")) {
            //Load gif
            Glide.with(context)
                    .load(images.get(position).getUrl())
                    .into(imageView);
        } else {
            Glide.with(context)
                    .load(images.get(position).getUrl())
                    .into(imageView);
        }

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}