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
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.util.ViewPreloadSizeProvider
import com.nicholasdoglio.eyebleach.R
import com.nicholasdoglio.eyebleach.db.RedditPost
import com.nicholasdoglio.eyebleach.di.injector
import com.nicholasdoglio.eyebleach.ui.util.SpacesItemDecoration
import com.nicholasdoglio.eyebleach.ui.util.calculateNumOfColumns
import kotlinx.android.synthetic.main.fragment_photo_list.*
import kotlinx.android.synthetic.main.item_photo_list.*
import kotlinx.coroutines.launch
import timber.log.Timber

class PhotoListFragment : Fragment(R.layout.fragment_photo_list) {

    private lateinit var photoListAdapter: PhotoListAdapter

    private val viewModel by viewModels<PhotoListViewModel> {
        requireActivity().injector.viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)

        requireActivity().window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark)
        }

        setupRecyclerView()

        swipeRefreshLayout.setOnRefreshListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.refreshTrigger.send(Unit)
            }
        }

        viewModel.refreshStatus.observe(viewLifecycleOwner, Observer { swipeRefreshLayout.isRefreshing = it })

        viewModel.posts.observe(viewLifecycleOwner, Observer {
            photoListAdapter.submitList(it)
            Timber.d("SIZE: ${it.size}")
        }
        )
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
        else -> super.onOptionsItemSelected(item)
    }

    private fun setupRecyclerView() {
        val manager = Glide.with(this)

        val sizeProvider = ViewPreloadSizeProvider<RedditPost>()

        photoListAdapter =
            PhotoListAdapter(
                sizeProvider,
                manager.asDrawable()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            )

        val preloader: RecyclerViewPreloader<RedditPost> =
            RecyclerViewPreloader(
                manager,
                photoListAdapter,
                sizeProvider,
                MAX_PRELOAD
            )

        recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), calculateNumOfColumns())
            adapter = photoListAdapter
            addItemDecoration(SpacesItemDecoration(SPACE_SIZE))
            addOnScrollListener(preloader)
            setRecyclerListener {
                if (it is PhotoListAdapter.PhotoGridViewHolder) {
                    manager.clear(it.galleryImage)
                }
            }
        }
    }

    companion object {
        private const val MAX_PRELOAD = 12
        private const val SPACE_SIZE = 24
    }
}
