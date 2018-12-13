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
package com.nicholasdoglio.eyebleach.ui.photodetail

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.nicholasdoglio.data.model.reddit.RedditPost

/**
 * @author Nicholas Doglio
 */
class PhotoDetailAdapter :
  PagedListAdapter<RedditPost, androidx.recyclerview.widget.RecyclerView.ViewHolder>(photoDetailDiff) {
  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

  override fun onBindViewHolder(
    holder: androidx.recyclerview.widget.RecyclerView.ViewHolder,
    position: Int
  ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

  inner class PhotoDetailViewHolder(itemView: View) :
    androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView)

    companion object {
        private val photoDetailDiff = object : DiffUtil.ItemCallback<RedditPost>() {
            override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean =
                oldItem == newItem
        }
    }

}