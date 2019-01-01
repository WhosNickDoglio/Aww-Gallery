package com.nicholasdoglio.eyebleach.ui.photolist

/**
 * @author Nicholas Doglio
 */
//class PhotoListAdapter :
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
////    if (currentList!!.toMutableList().isEmpty()) {
////      return emptyList<RedditPost>().toMutableList()
////    } else {
////      return currentList!!.toMutableList()
////    }
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
//}