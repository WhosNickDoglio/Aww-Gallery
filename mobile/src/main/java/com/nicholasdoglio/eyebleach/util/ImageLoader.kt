package com.nicholasdoglio.eyebleach.util

import android.content.Context
import android.widget.ImageView
import com.nicholasdoglio.data.model.reddit.RedditPost
import com.nicholasdoglio.eyebleach.R

/**
 * Created by nicholasdoglio on 2/1/18.
 */
class ImageLoader {

    //TODO move this to extension functions
    fun loadGridThumbnail(context: Context, imageView: ImageView, redditPost: RedditPost) {
        GlideApp
            .with(context)
            .load(redditPost.thumbnail)
            .error(R.drawable.cat_error)
            .into(imageView)
    }

    fun loadImage(context: Context, imageView: ImageView, redditPost: RedditPost) {
        GlideApp
            .with(context)
            .load(redditPost.url)
            .into(imageView)
    }
}