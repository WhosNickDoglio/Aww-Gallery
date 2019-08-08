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

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.ListPreloader
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.signature.ObjectKey
import com.bumptech.glide.util.ViewPreloadSizeProvider
import com.nicholasdoglio.eyebleach.R
import com.nicholasdoglio.eyebleach.db.RedditPost
import com.nicholasdoglio.eyebleach.ui.base.AwwGalleryHolder
import com.nicholasdoglio.eyebleach.ui.util.CircularProgressPlaceholderListener
import kotlinx.android.synthetic.main.item_photo_list.*

class PhotoListAdapter(
    private val sizeProvider: ViewPreloadSizeProvider<RedditPost>,
    private val request: RequestBuilder<Drawable>
) : PagedListAdapter<RedditPost,
    PhotoListAdapter.PhotoGridViewHolder>(diff),
    ListPreloader.PreloadModelProvider<RedditPost> {

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
                    oldItem.name == newItem.name
            }
    }

    override fun onBindViewHolder(holder: PhotoGridViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGridViewHolder =
        PhotoGridViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_photo_list,
                parent,
                false
            )
        )

    // TODO make sublist bigger?
    override fun getPreloadItems(position: Int): MutableList<RedditPost> =
        currentList?.subList(position, position + 1) ?: mutableListOf()

    override fun getPreloadRequestBuilder(item: RedditPost): RequestBuilder<*>? =
        request.load(item.thumbnail).error(R.drawable.cat_error)

    inner class PhotoGridViewHolder(override val containerView: View) :
        AwwGalleryHolder<RedditPost>(containerView) {

        override fun bind(model: RedditPost) {
            sizeProvider.setView(containerView)
            galleryImage.setOnClickListener {
                findNavController(containerView).navigate(
                    PhotoListFragmentDirections.openDetails(
                        model.name
                    )
                )
            }

            val placeholder = CircularProgressDrawable(galleryImage.context).apply {
                setStyle(CircularProgressDrawable.DEFAULT)
                setColorSchemeColors(
                    ContextCompat.getColor(
                        galleryImage.context,
                        R.color.colorAccent
                    )
                )
            }

            placeholder.start()

            request
                .load(model.thumbnail)
                .placeholder(placeholder)
                .transition(withCrossFade())
                .listener(CircularProgressPlaceholderListener(placeholder))
                .signature(ObjectKey(model.thumbnail))
                .into(galleryImage)
        }

        // companion object {
        //     fun create(parent: ViewGroup): PhotoGridViewHolder =
        //         PhotoGridViewHolder(
        //             LayoutInflater.from(parent.context).inflate(
        //                 R.layout.item_photo_list,
        //                 parent,
        //                 false
        //             )
        //         )
        // }
    }
}
