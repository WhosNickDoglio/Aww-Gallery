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

package com.nicholasdoglio.eyebleach.ui.photolist

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.nicholasdoglio.eyebleach.db.RedditPost

class PhotoListAdapter : PagedListAdapter<RedditPost, PhotoListViewHolder>(diff) {

    companion object {
        private val diff: DiffUtil.ItemCallback<RedditPost> =
            object : DiffUtil.ItemCallback<RedditPost>() {

                override fun areItemsTheSame(
                    oldItem: RedditPost,
                    newItem: RedditPost
                ): Boolean =
                    oldItem.name == newItem.name

                override fun areContentsTheSame(
                    oldItem: RedditPost,
                    newItem: RedditPost
                ): Boolean =
                    RedditPost.Impl(
                        oldItem.url,
                        oldItem.name,
                        oldItem.thumbnail,
                        oldItem.permalink
                    ) == RedditPost.Impl(
                        newItem.url,
                        newItem.name,
                        newItem.thumbnail,
                        newItem.permalink
                    )
            }
    }

    override fun onBindViewHolder(holder: PhotoListViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoListViewHolder =
        PhotoListViewHolder.create(parent)
}
