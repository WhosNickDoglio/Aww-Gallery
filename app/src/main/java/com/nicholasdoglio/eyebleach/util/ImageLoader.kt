package com.nicholasdoglio.eyebleach.util

import android.content.Context
import android.widget.ImageView
import com.nicholasdoglio.eyebleach.R
import com.nicholasdoglio.eyebleach.data.model.reddit.ChildData

/**
 * Created by nicholasdoglio on 2/1/18.
 */
class ImageLoader {

    fun loadGridThumbnail(context: Context, imageView: ImageView, childData: ChildData) {
        GlideApp
            .with(context)
            .load(childData.thumbnail)
            .error(R.drawable.cat_error)
            .into(imageView)
    }

    fun loadImage(context: Context, imageView: ImageView, childData: ChildData) {
        GlideApp
            .with(context)
            .load(childData.url)
            .into(imageView)
    }
}