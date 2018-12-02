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
package com.nicholasdoglio.eyebleach.ui.photolist

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.ListPreloader.PreloadModelProvider
import com.bumptech.glide.RequestBuilder
import com.nicholasdoglio.data.model.reddit.RedditPost

/**
 * @author Nicholas Doglio
 */
class PhotoListAdapter : PagedListAdapter<RedditPost, RecyclerView.ViewHolder>(photoListDiff),
    PreloadModelProvider<RedditPost> {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPreloadItems(position: Int): MutableList<RedditPost> {
        if (currentList!!.toMutableList().isEmpty()) {
            return emptyList<RedditPost>().toMutableList()
        } else {
            return currentList!!.toMutableList()
        }
    }

    override fun getPreloadRequestBuilder(item: RedditPost): RequestBuilder<*>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    inner class PhotoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {
        private val photoListDiff = object : DiffUtil.ItemCallback<RedditPost>() {
            override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean =
                oldItem == newItem
        }
    }
}