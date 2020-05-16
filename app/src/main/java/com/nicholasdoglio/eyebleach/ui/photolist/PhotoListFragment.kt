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

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.util.ViewPreloadSizeProvider
import com.nicholasdoglio.eyebleach.R
import com.nicholasdoglio.eyebleach.data.local.RedditPost
import com.nicholasdoglio.eyebleach.di.ViewModelFactory
import com.nicholasdoglio.eyebleach.ui.base.AwwGalleryFragment
import com.nicholasdoglio.eyebleach.ui.util.SpacesItemDecoration
import com.nicholasdoglio.eyebleach.util.calculateNoOfColumns
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_photo_list.*
import kotlinx.android.synthetic.main.item_photo_list.*

class PhotoListFragment @Inject constructor(
    override val factory: ViewModelFactory
) : AwwGalleryFragment<PhotoListViewModel>(factory, R.layout.fragment_photo_list) {

    private lateinit var photoListAdapter: PhotoListAdapter

    private val viewModel by viewModels<PhotoListViewModel> { factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val manager = Glide.with(this)

        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)

        requireActivity().window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark)
        }

        photoListAdapter =
            PhotoListAdapter(manager.asDrawable().diskCacheStrategy(DiskCacheStrategy.ALL))

        // TODO can I improve Glide integration with the RecyclerView
        val preloader: RecyclerViewPreloader<RedditPost> =
            RecyclerViewPreloader(
                manager,
                photoListAdapter,
                ViewPreloadSizeProvider<RedditPost>(),
                MAX_PRELOAD
            )

        recyclerView.apply {
            layoutManager = GridLayoutManager(context, calculateNoOfColumns())
            adapter = photoListAdapter
            addItemDecoration(SpacesItemDecoration(SPACE_SIZE))
            addOnScrollListener(preloader)
            setRecyclerListener {
                if (it is PhotoListAdapter.PhotoGridViewHolder) {
                    manager.clear(it.galleryImage)
                }
            }
        }

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }

        viewModel.refreshStatus.observe(viewLifecycleOwner, Observer { swipeRefreshLayout.isRefreshing = it })

        viewModel.posts.observe(viewLifecycleOwner, Observer { photoListAdapter.submitList(it) })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.grid_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.about_item -> {
            findNavController().navigate(R.id.open_about)
            true
        }
        R.id.libs_item -> {
            findNavController().navigate(R.id.open_libs)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    companion object {
        private const val MAX_PRELOAD = 50
        private const val SPACE_SIZE = 16
    }
}
