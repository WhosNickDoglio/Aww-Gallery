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

/**
 * @author Nicholas Doglio
 */
// class PhotoListAdapter :
//  PagedListAdapter<RedditPost, RecyclerView.ViewHolder>(photoListDiff), PreloadModelProvider<RedditPost> {
//
//  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//  }
//
//  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//  }
//
//  override fun getPreloadItems(position: Int): MutableList<RedditPost> {
//    //I don't think this works
// //    if (currentList!!.toMutableList().isEmpty()) {
// //      return emptyList<RedditPost>().toMutableList()
// //    } else {
// //      return currentList!!.toMutableList()
// //    }
//  }
//
//  override fun getPreloadRequestBuilder(item: RedditPost): RequestBuilder<*>? {
//    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//  }
//
//  inner class PhotoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//  }
//
//  companion object {
//    private val photoListDiff = object : DiffUtil.ItemCallback<RedditPost>() {
//      override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean =
//        oldItem.id == newItem.id
//
//      override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean =
//        oldItem == newItem
//    }
//  }
// }