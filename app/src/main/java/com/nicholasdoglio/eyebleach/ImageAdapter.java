package com.nicholasdoglio.eyebleach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nicholasdoglio.eyebleach.model.Child;
import com.nicholasdoglio.eyebleach.model.RedditPost;

import java.util.ArrayList;
import java.util.List;


public class ImageAdapter extends ArrayAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Child> data = new ArrayList<>();

    public ImageAdapter(Context context, List<Child> imageUrls) {
        super(context, R.layout.picture_item, imageUrls);

        this.context = context;
        this.data = imageUrls;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.picture_item, parent, false);
        }

        Glide.with(context).load(data.get(position).getData().getUrl()).into((ImageView) convertView);

        return convertView;
    }
}
