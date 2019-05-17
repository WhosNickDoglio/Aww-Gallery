/*
 * MIT License
 *
 * Copyright (c) 2019 Nicholas Doglio
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.nicholasdoglio.eyebleach.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.nicholasdoglio.eyebleach.R
import com.nicholasdoglio.eyebleach.data.local.RedditPost
import com.nicholasdoglio.eyebleach.data.remote.ChildData
import com.nicholasdoglio.eyebleach.data.remote.ListingResponse

/**
 * @author Nicholas Doglio
 */
fun Context.openWebPage(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    if (intent.resolveActivity(this.packageManager) != null) {
        this.startActivity(intent)
    }
}

fun Context.shareUrl(url: String) {
    this.startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).apply {
        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        putExtra(Intent.EXTRA_TEXT, url)
        type = "text/plain"
    }, "Share your cute animals via: "))
}

fun RecyclerView.calculateNoOfColumns(): Int {
    val displayMetrics = context.resources.displayMetrics

    val dpWidth = displayMetrics.widthPixels / displayMetrics.density

    val imageSize =
        context.resources.getDimension(R.dimen.grid_item_size) / context.resources.displayMetrics.density

    return (dpWidth / imageSize).toInt()
}

inline fun <reified VIEW_MODEL : ViewModel> Fragment.createViewModel(factory: ViewModelProvider.Factory): VIEW_MODEL =
    ViewModelProviders.of(this, factory).get(VIEW_MODEL::class.java)

fun ListingResponse.toRedditPosts(): List<RedditPost> {
    val posts = mutableListOf<RedditPost>()

    this.data.children
        .filter { it.data.url.contains(".jpg") || it.data.url.contains(".png") }
        .forEach { posts.add(it.data.toRedditPost()) }

    return posts
}

fun ChildData.toRedditPost(): RedditPost =
    RedditPost(
        name = name,
        url = url,
        thumbnail = thumbnail,
        permalink = permalink
    )
